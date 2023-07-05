package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.ExtensionRepository;
import de.rki.demis.fhir.util.constant.RequestOperation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 694cae4 (some refactorings are done)
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
=======
import static de.rki.demis.fhir.util.constant.Constants.CREATE_OP;
import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;
=======
>>>>>>> acf3b2c (wip)
import static de.rki.demis.fhir.util.service.PersistenceService.persistTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ExtensionService implements BaseService<Extension> {
    private final ExtensionRepository repository;
    private final UriTypeService uriTypeService;
    private final TypeService typeService;


    public List<Extension> listAll() {
        return repository.findAll();
    }

    public Extension getOne(UUID extensionId) {
        Optional<Extension> extensionOp = repository.findById(extensionId);

        if (extensionOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, Extension.class.getSimpleName(), extensionId)
            );
        }

        return extensionOp.get();
    }

    public Extension create(@NotNull Extension newExtension) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        checkForUniqueness(newExtension, repository);
        persistExtensionComponents(newExtension, RequestOperation.Create);
=======
        persistExtensionComponents(newExtension, CREATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
        persistExtensionComponents(newExtension, RequestOperation.Create);
>>>>>>> acf3b2c (wip)
        newExtension.setId(null);
        return repository.save(newExtension);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public Extension update(UUID extensionId, @NotNull Extension update) throws ResourceNotFoundException {
        getOne(extensionId); // to check if the update exist
        persistExtensionComponents(update, RequestOperation.Update);
=======
    public void update(UUID extensionId, @NotNull Extension update) throws ResourceNotFoundException {
        // to check if the update exist
        getOne(extensionId);

        // to check the uniqueness of the update
        if (!extensionId.equals(update.getId())) {
            checkForUniqueness(update);
        }

<<<<<<< HEAD
        persistExtensionComponents(update, UPDATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
=======
    public Extension update(UUID extensionId, @NotNull Extension update) throws ResourceNotFoundException {
        getOne(extensionId); // to check if the update exist
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        persistExtensionComponents(update, RequestOperation.Update);
>>>>>>> acf3b2c (wip)
        update.setId(extensionId);
        return repository.save(update);
    }

    public void delete(UUID extensionId) {
        getOne(extensionId);
        repository.deleteById(extensionId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
<<<<<<< HEAD
    }

    private void persistExtensionComponents(@NotNull Extension extension, RequestOperation requestOperation) {
        // URL
        if (Objects.nonNull(extension.getUrl())) {
            extension.setUrl(persistEntity(extension.getUrl(), uriTypeService, requestOperation));
        }

        // VALUE
        if (Objects.nonNull(extension.getValue())) {
            extension.setValue(persistEntity(extension.getValue(), typeService, requestOperation));
        }
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    }

    private void persistExtensionComponents(@NotNull Extension extension, RequestOperation requestOperation) {
        // URL
        if (Objects.nonNull(extension.getUrl())) {
            extension.setUrl(persistEntity(extension.getUrl(), uriTypeService, requestOperation));
        }

        // VALUE
        if (Objects.nonNull(extension.getValue())) {
            extension.setValue(persistEntity(extension.getValue(), typeService, requestOperation));
        }
    }

}
