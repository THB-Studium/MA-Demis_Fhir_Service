package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.BundleEntryComponent;
import de.rki.demis.fhir.model.BundleLinkComponent;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.BundleEntryComponentRepository;
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

import static de.rki.demis.fhir.util.service.PersistenceService.persistBundleEntryRequestComponentEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistBundleEntryResponseComponentEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistBundleEntrySearchComponentEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistBundleLinkComponentEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistExtensionEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistResourceEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntryComponentService {
    private final BundleEntryComponentRepository repository;
    private final ExtensionService extensionService;
    private final BundleLinkComponentService bundleLinkComponentService;
    private final BundleEntrySearchComponentService bundleEntrySearchComponentService;
    private final BundleEntryRequestComponentService bundleEntryRequestComponentService;
    private final BundleEntryResponseComponentService bundleEntryResponseComponentService;
    private final UriTypeService uriTypeService;
    private final ResourceService resourceService;


    public List<BundleEntryComponent> listAll() {
        return repository.findAll();
    }

    public BundleEntryComponent getOne(UUID bundleEntryComponentId) {
        Optional<BundleEntryComponent> bundleEntryComponentOp = repository.findById(bundleEntryComponentId);

        if (bundleEntryComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A BundleEntryComponent with 'id = %s' does not exist :::", bundleEntryComponentId)
            );
        }

        return bundleEntryComponentOp.get();
    }

    public BundleEntryComponent create(@NotNull BundleEntryComponent newBundleEntryComponent) {
        persistBundleEntryComponentComponents(newBundleEntryComponent, RequestOperation.Create);
        newBundleEntryComponent.setId(null);
        return repository.save(newBundleEntryComponent);
    }

    public void update(UUID bundleEntryComponentId, @NotNull BundleEntryComponent update)
            throws ResourceNotFoundException {
        getOne(bundleEntryComponentId);

        if (!bundleEntryComponentId.equals(update.getId())) {
            checkForUniqueness(update);
        }

        persistBundleEntryComponentComponents(update, RequestOperation.Update);
        update.setId(bundleEntryComponentId);
        repository.save(update);
    }

    public void delete(UUID bundleEntryComponentId) {
        getOne(bundleEntryComponentId);
        repository.deleteById(bundleEntryComponentId);
    }

    private void checkForUniqueness(@NotNull BundleEntryComponent bundleEntryComponent) {
        if (repository.existsById(bundleEntryComponent.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BundleEntryComponent with the id=%s already exist :::",
                            bundleEntryComponent.getId())
            );
        }
    }

    private void persistBundleEntryComponentComponents(@NotNull BundleEntryComponent bundleEntryComponent, RequestOperation requestOperation) {
        Set<Extension> modifierExtensions = new HashSet<>();
        Set<Extension> extensions = new HashSet<>();
        Set<BundleLinkComponent> links = new HashSet<>();

        // ModifierExtension
        if (Objects.nonNull(bundleEntryComponent.getModifierExtension())) {
            bundleEntryComponent.getModifierExtension().forEach(item ->
                    modifierExtensions.add(persistExtensionEntity(item, extensionService, requestOperation)));
        }

        // Extension
        if (Objects.nonNull(bundleEntryComponent.getExtension())) {
            bundleEntryComponent.getExtension().forEach(item ->
                    extensions.add(persistExtensionEntity(item, extensionService, requestOperation)));
        }

        // Link
        if (Objects.nonNull(bundleEntryComponent.getLink())) {
            bundleEntryComponent.getLink().forEach(item ->
                    links.add(persistBundleLinkComponentEntity(item, bundleLinkComponentService, requestOperation)));
        }

        // FullUrl
        bundleEntryComponent.setFullUrl(persistUriTypeEntity(bundleEntryComponent.getFullUrl(), uriTypeService, requestOperation));

        // Resource
        bundleEntryComponent.setResource(persistResourceEntity(bundleEntryComponent.getResource(), resourceService, requestOperation));

        // Search
        bundleEntryComponent.setSearch(
                persistBundleEntrySearchComponentEntity(bundleEntryComponent.getSearch(), bundleEntrySearchComponentService, requestOperation));

        // Request
        bundleEntryComponent.setRequest(
                persistBundleEntryRequestComponentEntity(bundleEntryComponent.getRequest(), bundleEntryRequestComponentService, requestOperation));

        // Response
        bundleEntryComponent.setResponse(
                persistBundleEntryResponseComponentEntity(bundleEntryComponent.getResponse(), bundleEntryResponseComponentService, requestOperation));


        bundleEntryComponent.setModifierExtension(modifierExtensions);
        bundleEntryComponent.setExtension(extensions);
        bundleEntryComponent.setLink(links);
    }

}
