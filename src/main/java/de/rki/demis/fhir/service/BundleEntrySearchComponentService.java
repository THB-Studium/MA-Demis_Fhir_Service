package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.BundleEntrySearchComponent;
import de.rki.demis.fhir.repository.BundleEntrySearchComponentRepository;
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
public class BundleEntrySearchComponentService {
    private final BundleEntrySearchComponentRepository repository;


    public List<BundleEntrySearchComponent> listAll() {
        return repository.findAll();
    }

    public BundleEntrySearchComponent getOne(UUID bundleEntrySearchComponent) {
        Optional<BundleEntrySearchComponent> bundleEntrySearchComponentOp = repository.findById(bundleEntrySearchComponent);

        if (bundleEntrySearchComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A BundleEntrySearchComponent with 'id = %s' does not exist :::", bundleEntrySearchComponent)
            );
        }

        return bundleEntrySearchComponentOp.get();
    }

    public BundleEntrySearchComponent create(@NotNull BundleEntrySearchComponent newBundleEntrySearchComponent) {
        newBundleEntrySearchComponent.setId(null);
        return repository.save(newBundleEntrySearchComponent);
    }

    public void update(UUID bundleEntrySearchComponent, @NotNull BundleEntrySearchComponent update) throws ResourceNotFoundException {
        getOne(bundleEntrySearchComponent);

        if (!bundleEntrySearchComponent.equals(update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(bundleEntrySearchComponent);
        repository.save(update);
    }

    public void delete(UUID bundleEntrySearchComponent) {
        getOne(bundleEntrySearchComponent);
        repository.deleteById(bundleEntrySearchComponent);
    }

    private void checkForUniqueness(@NotNull BundleEntrySearchComponent bundleEntrySearchComponent) {
        if (repository.existsById(bundleEntrySearchComponent.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BundleEntrySearchComponent with the id=%s already exist :::", bundleEntrySearchComponent.getId())
            );
        }
    }

}
