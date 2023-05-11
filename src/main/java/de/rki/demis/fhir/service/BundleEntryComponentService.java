package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.BundleEntryComponent;
import de.rki.demis.fhir.model.BundleLinkComponent;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.BundleEntryComponentRepository;
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
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
=======
import static de.rki.demis.fhir.util.service.PersistenceService.persistBundleEntryRequestComponentEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistBundleEntryResponseComponentEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistBundleEntrySearchComponentEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistBundleLinkComponentEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistExtensionEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistResourceEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;
>>>>>>> acf3b2c (wip)

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntryComponentService implements BaseService<BundleEntryComponent> {
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
                    String.format(NOT_EXIST_MSG, BundleEntryComponent.class.getSimpleName(), bundleEntryComponentId)
            );
        }

        return bundleEntryComponentOp.get();
    }

    public BundleEntryComponent create(@NotNull BundleEntryComponent newBundleEntryComponent) {
<<<<<<< HEAD
        checkForUniqueness(newBundleEntryComponent, repository);
=======
>>>>>>> acf3b2c (wip)
        persistBundleEntryComponentComponents(newBundleEntryComponent, RequestOperation.Create);
        newBundleEntryComponent.setId(null);
        return repository.save(newBundleEntryComponent);
    }

    public BundleEntryComponent update(UUID bundleEntryComponentId, @NotNull BundleEntryComponent update)
            throws ResourceNotFoundException {
        getOne(bundleEntryComponentId);
<<<<<<< HEAD
=======

        if (!bundleEntryComponentId.equals(update.getId())) {
            checkForUniqueness(update);
        }

>>>>>>> acf3b2c (wip)
        persistBundleEntryComponentComponents(update, RequestOperation.Update);
        update.setId(bundleEntryComponentId);
        return repository.save(update);
    }

    public void delete(UUID bundleEntryComponentId) {
        getOne(bundleEntryComponentId);
        repository.deleteById(bundleEntryComponentId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

    private void persistBundleEntryComponentComponents(@NotNull BundleEntryComponent bundleEntryComponent, RequestOperation requestOperation) {
        Set<Extension> modifierExtensions = new HashSet<>();
        Set<Extension> extensions = new HashSet<>();
        Set<BundleLinkComponent> links = new HashSet<>();

        // ModifierExtension
        if (Objects.nonNull(bundleEntryComponent.getModifierExtension())) {
            bundleEntryComponent.getModifierExtension().forEach(item ->
                    modifierExtensions.add(persistEntity(item, extensionService, requestOperation))
            );
        }

        // Extension
        if (Objects.nonNull(bundleEntryComponent.getExtension())) {
            bundleEntryComponent.getExtension().forEach(item ->
                    extensions.add(persistEntity(item, extensionService, requestOperation))
            );
        }

        // Link
        if (Objects.nonNull(bundleEntryComponent.getLink())) {
            bundleEntryComponent.getLink().forEach(item ->
                    links.add(persistEntity(item, bundleLinkComponentService, requestOperation))
            );
        }

        // FullUrl
        bundleEntryComponent.setFullUrl(persistEntity(bundleEntryComponent.getFullUrl(), uriTypeService, requestOperation));

        // Resource
        bundleEntryComponent.setResource(persistEntity(bundleEntryComponent.getResource(), resourceService, requestOperation));

        // Search
        bundleEntryComponent.setSearch(persistEntity(bundleEntryComponent.getSearch(), bundleEntrySearchComponentService, requestOperation));

        // Request
        bundleEntryComponent.setRequest(persistEntity(bundleEntryComponent.getRequest(), bundleEntryRequestComponentService, requestOperation));

        // Response
        bundleEntryComponent.setResponse(persistEntity(bundleEntryComponent.getResponse(), bundleEntryResponseComponentService, requestOperation));


        bundleEntryComponent.setModifierExtension(modifierExtensions);
        bundleEntryComponent.setExtension(extensions);
        bundleEntryComponent.setLink(links);
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
