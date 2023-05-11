package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.BundleEntryResponseComponent;
import de.rki.demis.fhir.repository.BundleEntryResponseComponentRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntryResponseComponentService {
    private final BundleEntryResponseComponentRepository repository;


    public List<BundleEntryResponseComponent> listAll() {
        return repository.findAll();
    }

    public BundleEntryResponseComponent getOne(UUID bundleEntryResponseComponentId) {
        Optional<BundleEntryResponseComponent> bundleEntryResponseComponentOp = repository.findById(bundleEntryResponseComponentId);

        if (bundleEntryResponseComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A BundleEntryResponseComponent with 'id = %s' does not exist :::", bundleEntryResponseComponentId)
            );
        }

        return bundleEntryResponseComponentOp.get();
    }

    public BundleEntryResponseComponent create(@NotNull BundleEntryResponseComponent newBundleEntryResponseComponent) {
        newBundleEntryResponseComponent.setId(null);
        return repository.save(newBundleEntryResponseComponent);
    }

    public void update(UUID bundleEntryResponseComponentId, @NotNull BundleEntryResponseComponent update) throws ResourceNotFoundException {
        getOne(bundleEntryResponseComponentId);

        if (!bundleEntryResponseComponentId.equals(update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(bundleEntryResponseComponentId);
        repository.save(update);
    }

    public void delete(UUID bundleEntryResponseComponentId) {
        getOne(bundleEntryResponseComponentId);
        repository.deleteById(bundleEntryResponseComponentId);
    }

    private void checkForUniqueness(@NotNull BundleEntryResponseComponent bundleEntryResponseComponent) {
        if (repository.existsById(bundleEntryResponseComponent.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BundleEntryResponseComponent with the id=%s already exist :::", bundleEntryResponseComponent.getId())
            );
        }
    }

}
