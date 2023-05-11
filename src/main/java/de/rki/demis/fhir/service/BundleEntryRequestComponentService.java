package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.BundleEntryRequestComponent;
import de.rki.demis.fhir.repository.BundleEntryRequestComponentRepository;
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
public class BundleEntryRequestComponentService {
    private final BundleEntryRequestComponentRepository repository;


    public List<BundleEntryRequestComponent> listAll() {
        return repository.findAll();
    }

    public BundleEntryRequestComponent getOne(UUID bundleEntryRequestComponentId) {
        Optional<BundleEntryRequestComponent> bundleEntryRequestComponentOp = repository.findById(bundleEntryRequestComponentId);

        if (bundleEntryRequestComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A BundleEntryRequestComponent with 'id = %s' does not exist :::", bundleEntryRequestComponentId)
            );
        }

        return bundleEntryRequestComponentOp.get();
    }

    public BundleEntryRequestComponent create(@NotNull BundleEntryRequestComponent newBundleEntryRequestComponent) {
        newBundleEntryRequestComponent.setId(null);
        return repository.save(newBundleEntryRequestComponent);
    }

    public void update(UUID bundleEntryRequestComponentId, @NotNull BundleEntryRequestComponent update) throws ResourceNotFoundException {
        getOne(bundleEntryRequestComponentId);

        if (!bundleEntryRequestComponentId.equals(update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(bundleEntryRequestComponentId);
        repository.save(update);
    }

    public void delete(UUID bundleEntryRequestComponentId) {
        getOne(bundleEntryRequestComponentId);
        repository.deleteById(bundleEntryRequestComponentId);
    }

    private void checkForUniqueness(@NotNull BundleEntryRequestComponent bundleEntryRequestComponent) {
        if (repository.existsById(bundleEntryRequestComponent.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BundleEntryRequestComponent with the id=%s already exist :::", bundleEntryRequestComponent.getId())
            );
        }
    }

}
