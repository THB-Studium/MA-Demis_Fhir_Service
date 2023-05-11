package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.ExtensionRepository;
import de.rki.demis.fhir.util.constant.RequestOperation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static de.rki.demis.fhir.util.service.PersistenceService.persistTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ExtensionService {
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
                    String.format("::: A Extension with 'id = %s' does not exist :::", extensionId)
            );
        }

        return extensionOp.get();
    }

    public Extension create(@NotNull Extension newExtension) {
        persistExtensionComponents(newExtension, RequestOperation.Create);
        newExtension.setId(null);
        return repository.save(newExtension);
    }

    public void update(UUID extensionId, @NotNull Extension update) throws ResourceNotFoundException {
        // to check if the update exist
        getOne(extensionId);

        // to check the uniqueness of the update
        if (!extensionId.equals(update.getId())) {
            checkForUniqueness(update);
        }

        persistExtensionComponents(update, RequestOperation.Update);
        update.setId(extensionId);
        repository.save(update);
    }

    public void delete(UUID extensionId) {
        getOne(extensionId);
        repository.deleteById(extensionId);
    }

    private void checkForUniqueness(@NotNull Extension extension) {
        if (repository.existsById(extension.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Extension with the id=%s already exist :::", extension.getId())
            );
        }
    }

    private void persistExtensionComponents(@NotNull Extension extension, RequestOperation requestOperation) {
        // URL
        if (Objects.nonNull(extension.getUrl())) {
            extension.setUrl(persistUriTypeEntity(extension.getUrl(), uriTypeService, requestOperation));
        }

        // VALUE
        if (Objects.nonNull(extension.getValue())) {
            extension.setValue(persistTypeEntity(extension.getValue(), typeService, requestOperation));
        }
    }

}
