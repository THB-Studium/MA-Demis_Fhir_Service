package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.model.Reference;
import de.rki.demis.fhir.repository.ReferenceRepository;
import de.rki.demis.fhir.util.constant.RequestOperation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

<<<<<<< HEAD
<<<<<<< HEAD
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
=======
import static de.rki.demis.fhir.util.constant.Constants.CREATE_OP;
import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;
=======
>>>>>>> acf3b2c (wip)
import static de.rki.demis.fhir.util.service.PersistenceService.persistExtensionEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistIdentifierEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;
>>>>>>> c598496 (update issues in BinaryService fixed)

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ReferenceService implements BaseService<Reference> {
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
                    String.format(NOT_EXIST_MSG, Reference.class.getSimpleName(), referenceId)
            );
        }

        return referenceOp.get();
    }

    public Reference create(@NotNull Reference newReference) {
<<<<<<< HEAD
<<<<<<< HEAD
        checkForUniqueness(newReference, repository);
        persistReferenceComponents(newReference, RequestOperation.Create);
=======
        persistReferenceComponents(newReference, CREATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
        persistReferenceComponents(newReference, RequestOperation.Create);
>>>>>>> acf3b2c (wip)
        newReference.setId(null);
        return repository.save(newReference);
    }

<<<<<<< HEAD
    public Reference update(UUID referenceId, @NotNull Reference update) throws ResourceNotFoundException {
        getOne(referenceId); // to check if the update exist
        persistReferenceComponents(update, RequestOperation.Update);
=======
    public void update(UUID referenceId, @NotNull Reference update) throws ResourceNotFoundException {
        getOne(referenceId);

        if (!referenceId.equals(update.getId())) {
            checkForUniqueness(update);
        }

<<<<<<< HEAD
        persistReferenceComponents(update, UPDATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
        persistReferenceComponents(update, RequestOperation.Update);
>>>>>>> acf3b2c (wip)
        update.setId(referenceId);
        return repository.save(update);
    }

    public void delete(UUID referenceId) {
        getOne(referenceId);
        repository.deleteById(referenceId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

    private void persistReferenceComponents(@NotNull Reference reference, RequestOperation requestOperation) {
        Set<Extension> extension = new HashSet<>();

        // extension
        if (Objects.nonNull(reference.getExtension())) {
            reference.getExtension().forEach(item ->
                    extension.add(persistEntity(item, extensionService, requestOperation))
            );
        }

        // Type
        if (Objects.nonNull(reference.getType())) {
            reference.setType(persistEntity(reference.getType(), uriTypeService, requestOperation));
        }

        // Identifier
        if (Objects.nonNull(reference.getIdentifier())) {
            reference.setIdentifier(persistEntity(reference.getIdentifier(), identifierService, requestOperation));
        }

        reference.setExtension(extension);
    }

    private void persistReferenceComponents(@NotNull Reference reference, RequestOperation requestOperation) {
        Set<Extension> extension = new HashSet<>();

        // extension
        if (Objects.nonNull(reference.getExtension())) {
            reference.getExtension().forEach(item ->
                    extension.add(persistExtensionEntity(item, extensionService, requestOperation)));
        }

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
