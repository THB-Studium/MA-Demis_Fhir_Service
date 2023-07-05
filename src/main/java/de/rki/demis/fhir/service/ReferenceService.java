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

import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

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
                    String.format("::: A Reference with 'id = %s' does not exist :::", referenceId)
            );
        }

        return referenceOp.get();
    }

    public Reference create(@NotNull Reference newReference) {
        checkForUniqueness(newReference, repository);
        persistReferenceComponents(newReference, RequestOperation.Create);
        newReference.setId(null);
        return repository.save(newReference);
    }

    public Reference update(UUID referenceId, @NotNull Reference update) throws ResourceNotFoundException {
        getOne(referenceId); // to check if the update exist
        persistReferenceComponents(update, RequestOperation.Update);
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

}
