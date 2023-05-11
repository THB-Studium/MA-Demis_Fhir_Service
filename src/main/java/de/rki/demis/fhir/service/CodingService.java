package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.CodingRepository;
import de.rki.demis.fhir.util.constant.RequestOperation;
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

import static de.rki.demis.fhir.util.service.PersistenceService.persistCodeTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistExtensionEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CodingService {
    private final CodingRepository repository;
    private final ExtensionService extensionService;
    private final UriTypeService uriTypeService;
    private final CodeTypeService codeTypeService;


    public List<Coding> listAll() {
        return repository.findAll();
    }

    public Coding getOne(UUID metaId) {
        Optional<Coding> metaOp = repository.findById(metaId);

        if (metaOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A Coding with 'id = %s' does not exist :::", metaId)
            );
        }

        return metaOp.get();
    }

    public Coding create(@NotNull Coding newCoding) {
        persistCodingComponents(newCoding, RequestOperation.Create);
        newCoding.setId(null);
        return repository.save(newCoding);
    }

    public void update(UUID metaId, @NotNull Coding update) throws ResourceNotFoundException {
        // to check if the update exist
        getOne(metaId);

        // to check the uniqueness of the update
        if (!metaId.equals(update.getId())) {
            checkForUniqueness(update);
        }

        persistCodingComponents(update, RequestOperation.Update);
        update.setId(metaId);
        repository.save(update);
    }

    public void delete(UUID metaId) {
        getOne(metaId);
        repository.deleteById(metaId);
    }

    private void checkForUniqueness(@NotNull Coding meta) {
        if (repository.existsById(meta.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Coding with the id=%s already exist :::", meta.getId())
            );
        }
    }

    private void persistCodingComponents(@NotNull Coding coding, RequestOperation requestOperation) {
        Set<Extension> extension = new HashSet<>();

        // Extension
        if (Objects.nonNull(coding.getExtension())) {
            coding.getExtension().forEach(item -> extension.add(persistExtensionEntity(item, extensionService, requestOperation)));
        }

        // System
        if (Objects.nonNull(coding.getSystem())) {
            coding.setSystem(persistUriTypeEntity(coding.getSystem(), uriTypeService, requestOperation));
        }

        // Code
        if (Objects.nonNull(coding.getCode())) {
            coding.setCode(persistCodeTypeEntity(coding.getCode(), codeTypeService, requestOperation));
        }

        coding.setExtension(extension);
    }

}
