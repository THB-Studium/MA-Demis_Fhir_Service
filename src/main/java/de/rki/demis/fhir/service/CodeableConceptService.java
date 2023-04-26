package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.CodeableConcept;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.CodeableConceptRepository;
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

import static de.rki.demis.fhir.util.constant.Constants.CREATE_OP;
import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;
import static de.rki.demis.fhir.util.service.PersistenceService.persistCodingEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistExtensionEntity;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CodeableConceptService {
    private final CodeableConceptRepository repository;
    private final CodingService codingService;
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
        persistCodeableConceptComponents(newCodeableConcept, CREATE_OP);
        newCodeableConcept.setId(null);
        return repository.save(newCodeableConcept);
    }

    public void update(UUID codeableConceptId, @NotNull CodeableConcept update) throws ResourceNotFoundException {
        getOne(codeableConceptId);

        if (!Objects.equals(codeableConceptId, update.getId())) {
            checkForUniqueness(update);
        }

        persistCodeableConceptComponents(update, UPDATE_OP);
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

    private void persistCodeableConceptComponents(@NotNull CodeableConcept codeableConcept, String requestOperation) {
        Set<Coding> coding = new HashSet<>();
        Set<Extension> extension = new HashSet<>();

        codeableConcept.getCoding().forEach(item ->
                coding.add(persistCodingEntity(item, codingService, requestOperation)));

        codeableConcept.getExtension().forEach(item ->
                extension.add(persistExtensionEntity(item, extensionService, requestOperation)));

        codeableConcept.setCoding(coding);
        codeableConcept.setExtension(extension);
    }

}
