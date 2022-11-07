package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.CodeableConcept;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.CodeableConceptRepository;
import de.rki.demis.fhir.repository.CodingRepository;
import de.rki.demis.fhir.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CodeableConceptService {
    private final CodeableConceptRepository repository;
    private final CodingRepository codingRepository;
    private final ExtensionRepository extensionRepository;
    private final ExtensionService extensionService;


    public List<CodeableConcept> listAll() {
        return repository.findAll();
    }

    public CodeableConcept getOne(UUID codeableConceptId) {
        Optional<CodeableConcept> codeableConceptOp = repository.findById(codeableConceptId);

        if (codeableConceptOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A CodeableConcept with 'id = %s' does not exist :::", codeableConceptId)
            );
        }

        return codeableConceptOp.get();
    }

    public CodeableConcept create(@NotNull CodeableConcept newCodeableConcept) {
        Set<Coding> coding = new HashSet<>();
        Set<Extension> extension = new HashSet<>();

        newCodeableConcept.getCoding().forEach(item -> {
            if (Objects.isNull(item.getId()) || !codingRepository.existsById(item.getId())) {
                item = codingRepository.save(item);
            }
            coding.add(item);
        });

        newCodeableConcept.getExtension().forEach(item -> {
            if (Objects.isNull(item.getId()) || !extensionRepository.existsById(item.getId())) {
                item = extensionService.create(item);
            }
            extension.add(item);
        });

        newCodeableConcept.setCoding(coding);
        newCodeableConcept.setExtension(extension);
        newCodeableConcept.setId(null);
        return repository.save(newCodeableConcept);
    }

    public void update(UUID codeableConceptId, @NotNull CodeableConcept update) throws ResourceNotFoundException {
        CodeableConcept codeableConceptFound = getOne(codeableConceptId);

        if (!Objects.equals(codeableConceptFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(codeableConceptId);
        repository.save(update);
    }

    public void delete(UUID codeableConceptId) {
        getOne(codeableConceptId);
        repository.deleteById(codeableConceptId);
    }

    private void checkForUniqueness(@NotNull CodeableConcept codeableConcept) {
        if (repository.existsById(codeableConcept.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A CodeableConcept with the id=%s already exist :::", codeableConcept.getId())
            );
        }
    }

}
