package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Resource;
import de.rki.demis.fhir.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ResourceService {
    private final ResourceRepository repository;
    private final MetaService metaService;
    private final UriTypeService uriTypeService;
    private final CodeTypeService codeTypeService;


    public List<Resource> listAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
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
        // Meta
        if (Objects.nonNull(newResource.getMeta())) {
            newResource.setMeta(metaService.create(newResource.getMeta()));
        }

        // ImplicitRules
        if (Objects.nonNull(newResource.getImplicitRules())) {
            newResource.setImplicitRules(uriTypeService
                    .create(newResource.getImplicitRules()));
        }

        // Language
        if (Objects.nonNull(newResource.getLanguage())) {
            newResource.setLanguage(codeTypeService
                    .create(newResource.getLanguage()));
        }

        newResource.setId(UUID.randomUUID());
        return repository.save(newResource);
    }

    public void update(UUID resourceId, @NotNull Resource update) throws ResourceNotFoundException {
        Resource resourceFound = getOne(resourceId);

        if (!Objects.equals(resourceFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

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

}
