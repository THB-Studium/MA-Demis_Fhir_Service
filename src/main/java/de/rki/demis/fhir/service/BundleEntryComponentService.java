package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.BundleEntryComponent;
import de.rki.demis.fhir.model.BundleLinkComponent;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.BundleEntryComponentRepository;
import de.rki.demis.fhir.repository.BundleEntryRequestComponentRepository;
import de.rki.demis.fhir.repository.BundleEntryResponseComponentRepository;
import de.rki.demis.fhir.repository.BundleEntrySearchComponentRepository;
import de.rki.demis.fhir.repository.BundleLinkComponentRepository;
import de.rki.demis.fhir.repository.ExtensionRepository;
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

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntryComponentService {
    private final BundleEntryComponentRepository repository;
    private final ExtensionRepository extensionRepository;
    private final BundleLinkComponentRepository bundleLinkComponentRepository;
    private final BundleEntrySearchComponentRepository bundleEntrySearchComponentRepository;
    private final BundleEntryRequestComponentRepository bundleEntryRequestComponentRepository;
    private final BundleEntryResponseComponentRepository bundleEntryResponseComponentRepository;
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
        // ModifierExtension
        Set<Extension> modifierExtension = new HashSet<>();
        if (Objects.nonNull(newBundleEntryComponent.getModifierExtension())) {
            newBundleEntryComponent.getModifierExtension().forEach(item -> {
                if (Objects.isNull(item.getId()) || !extensionRepository.existsById(item.getId())) {
                    item = extensionRepository.save(item);
                }
                modifierExtension.add(item);
            });
        }

        // Extension
        Set<Extension> extension = new HashSet<>();
        if (Objects.nonNull(newBundleEntryComponent.getExtension())) {
            newBundleEntryComponent.getExtension().forEach(item -> {
                if (Objects.isNull(item.getId()) || !extensionRepository.existsById(item.getId())) {
                    item = extensionRepository.save(item);
                }
                extension.add(item);
            });
        }

        // Link
        Set<BundleLinkComponent> link = new HashSet<>();
        if (Objects.nonNull(newBundleEntryComponent.getLink())) {
            newBundleEntryComponent.getLink().forEach(item -> {
                if (Objects.isNull(item.getId()) || !bundleLinkComponentRepository.existsById(item.getId())) {
                    item = bundleLinkComponentRepository.save(item);
                }
                link.add(item);
            });
        }

        // FullUrl
        if (Objects.nonNull(newBundleEntryComponent.getFullUrl())) {
            newBundleEntryComponent.setFullUrl(uriTypeService
                    .create(newBundleEntryComponent.getFullUrl()));
        }

        // Resource
        if (Objects.nonNull(newBundleEntryComponent.getResource())) {
            newBundleEntryComponent.setResource(resourceService
                    .create(newBundleEntryComponent.getResource()));
        }

        // Search
        if (Objects.nonNull(newBundleEntryComponent.getSearch())) {
            newBundleEntryComponent.setSearch(
                    Objects.isNull(newBundleEntryComponent.getSearch().getId())
                            ? bundleEntrySearchComponentRepository.save(newBundleEntryComponent.getSearch())
                            : newBundleEntryComponent.getSearch());
        }

        // Request
        if (Objects.nonNull(newBundleEntryComponent.getRequest())) {
            newBundleEntryComponent.setRequest(
                    Objects.isNull(newBundleEntryComponent.getRequest().getId())
                            ? bundleEntryRequestComponentRepository.save(newBundleEntryComponent.getRequest())
                            : newBundleEntryComponent.getRequest());
        }

        // Response
        if (Objects.nonNull(newBundleEntryComponent.getResponse())) {
            newBundleEntryComponent.setResponse(
                    Objects.isNull(newBundleEntryComponent.getResponse().getId())
                            ? bundleEntryResponseComponentRepository.save(newBundleEntryComponent.getResponse())
                            : newBundleEntryComponent.getResponse());
        }

        newBundleEntryComponent.setModifierExtension(modifierExtension);
        newBundleEntryComponent.setExtension(extension);
        newBundleEntryComponent.setLink(link);
        newBundleEntryComponent.setId(null);
        return repository.save(newBundleEntryComponent);
    }

    public void update(UUID bundleEntryComponentId, @NotNull BundleEntryComponent update)
            throws ResourceNotFoundException {
        BundleEntryComponent bundleEntryComponentFound = getOne(bundleEntryComponentId);

        if (!Objects.equals(bundleEntryComponentFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

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

}
