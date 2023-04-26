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

import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

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
        checkForUniqueness(newExtension, repository);
        persistExtensionComponents(newExtension, RequestOperation.Create);
        newExtension.setId(null);
        return repository.save(newExtension);
    }

    public Extension update(UUID extensionId, @NotNull Extension update) throws ResourceNotFoundException {
        getOne(extensionId); // to check if the update exist
        persistExtensionComponents(update, RequestOperation.Update);
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
