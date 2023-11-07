package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.repository.ResourceRepository;
import de.rki.demis.fhir.util.constant.RequestOperation;
import de.rki.demis.fhir.model.Resource;
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
public class ResourceService implements BaseService<Resource> {
    private final ResourceRepository repository;
    private final MetaService metaService;
    private final UriTypeService uriTypeService;
    private final CodeTypeService codeTypeService;


    public List<Resource> listAll() {
        return repository.findAll();
    }

    public Resource getOne(UUID resourceId) {
        Optional<Resource> resourceOp = repository.findById(resourceId);

        if (resourceOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, Resource.class.getSimpleName(), resourceId)
            );
        }

        return resourceOp.get();
    }

    public Resource create(@NotNull Resource newResource) {
        checkForUniqueness(newResource, repository);
        persistResourceComponents(newResource, RequestOperation.Create);
        newResource.setId(null);
        return repository.save(newResource);
    }

    public Resource update(UUID resourceId, @NotNull Resource update) throws ResourceNotFoundException {
        getOne(resourceId); // to check if the update exist
        persistResourceComponents(update, RequestOperation.Update);
        update.setId(resourceId);
        return repository.save(update);
    }

    public void delete(UUID resourceId) {
        getOne(resourceId);
        repository.deleteById(resourceId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

    private void persistResourceComponents(@NotNull Resource resource, RequestOperation requestOperation) {
        // Meta
        if (Objects.nonNull(resource.getMeta())) {
            resource.setMeta(persistEntity(resource.getMeta(), metaService, requestOperation));
        }

        // ImplicitRules
        if (Objects.nonNull(resource.getImplicitRules())) {
            resource.setImplicitRules(persistEntity(resource.getImplicitRules(), uriTypeService, requestOperation));
        }

        // Language
        if (Objects.nonNull(resource.getLanguage())) {
            resource.setLanguage(persistEntity(resource.getLanguage(), codeTypeService, requestOperation));
        }
    }

}
