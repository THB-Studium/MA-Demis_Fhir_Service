package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.repository.ResourceRepository;
import de.rki.demis.fhir.util.fhir_object.classes.Resource;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static de.rki.demis.fhir.util.constant.Constants.CREATE_OP;
import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;
import static de.rki.demis.fhir.util.service.PersistenceService.persistCodeTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistMetaEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ResourceService {
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
                    String.format("::: A Resource with 'id = %s' does not exist :::", resourceId)
            );
        }

        return resourceOp.get();
    }

    public Resource create(@NotNull Resource newResource) {
        persistResourceComponents(newResource, CREATE_OP);
        newResource.setId(null);
        return repository.save(newResource);
    }

    public void update(UUID resourceId, @NotNull Resource update) throws ResourceNotFoundException {
        getOne(resourceId);

        if (!Objects.equals(resourceId, update.getId())) {
            checkForUniqueness(update);
        }

        persistResourceComponents(update, UPDATE_OP);
        update.setId(resourceId);
        repository.save(update);
    }

    public void delete(UUID resourceId) {
        getOne(resourceId);
        repository.deleteById(resourceId);
    }

    private void checkForUniqueness(@NotNull Resource resource) {
        if (repository.existsById(resource.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Resource with the id=%s already exist :::", resource.getId())
            );
        }
    }

    private void persistResourceComponents(@NotNull Resource resource, String requestOperation) {
        // Meta
        if (Objects.nonNull(resource.getMeta())) {
            resource.setMeta(persistMetaEntity(resource.getMeta(), metaService, requestOperation));
        }

        // ImplicitRules
        if (Objects.nonNull(resource.getImplicitRules())) {
            resource.setImplicitRules(persistUriTypeEntity(resource.getImplicitRules(), uriTypeService, requestOperation));
        }

        // Language
        if (Objects.nonNull(resource.getLanguage())) {
            resource.setLanguage(persistCodeTypeEntity(resource.getLanguage(), codeTypeService, requestOperation));
        }
    }

}
