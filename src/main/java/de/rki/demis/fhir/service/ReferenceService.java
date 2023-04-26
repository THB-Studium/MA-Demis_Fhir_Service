package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.model.Reference;
import de.rki.demis.fhir.repository.ReferenceRepository;
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
import static de.rki.demis.fhir.util.service.PersistenceService.persistExtensionEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistIdentifierEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ReferenceService {
    private final ReferenceRepository repository;
    private final ExtensionService extensionService;
    private final UriTypeService uriTypeService;
    private final IdentifierService identifierService;


    public List<Reference> listAll() {
        return repository.findAll();
    }

    public Reference getOne(UUID referenceId) {
        Optional<Reference> referenceOp = repository.findById(referenceId);

        if (referenceOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A Reference with 'id = %s' does not exist :::", referenceId)
            );
        }

        return referenceOp.get();
    }

    public Reference create(@NotNull Reference newReference) {
        persistReferenceComponents(newReference, CREATE_OP);
        newReference.setId(null);
        return repository.save(newReference);
    }

    public void update(UUID referenceId, @NotNull Reference update) throws ResourceNotFoundException {
        getOne(referenceId);

        if (!Objects.equals(referenceId, update.getId())) {
            checkForUniqueness(update);
        }

        persistReferenceComponents(update, UPDATE_OP);
        update.setId(referenceId);
        repository.save(update);
    }

    public void delete(UUID referenceId) {
        getOne(referenceId);
        repository.deleteById(referenceId);
    }

    private void checkForUniqueness(@NotNull Reference reference) {
        if (repository.existsById(reference.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Reference with the id=%s already exist :::", reference.getId())
            );
        }
    }

    private void persistReferenceComponents(@NotNull Reference reference, String requestOperation) {
        // extension
        Set<Extension> extension = new HashSet<>();
        reference.getExtension().forEach(item ->
                extension.add(persistExtensionEntity(item, extensionService, requestOperation)));

        // Type
        if (Objects.nonNull(reference.getType())) {
            reference.setType(persistUriTypeEntity(reference.getType(), uriTypeService, requestOperation));
        }

        // Identifier
        if (Objects.nonNull(reference.getIdentifier())) {
            reference.setIdentifier(persistIdentifierEntity(reference.getIdentifier(), identifierService, requestOperation));
        }

        reference.setExtension(extension);
    }

}
