package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.BundleEntrySearchComponent;
import de.rki.demis.fhir.repository.BundleEntrySearchComponentRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntrySearchComponentService implements BaseService<BundleEntrySearchComponent> {
    private final BundleEntrySearchComponentRepository repository;


    public List<BundleEntrySearchComponent> listAll() {
        return repository.findAll();
    }

    public BundleEntrySearchComponent getOne(UUID bundleEntrySearchComponent) {
        Optional<BundleEntrySearchComponent> bundleEntrySearchComponentOp = repository.findById(bundleEntrySearchComponent);

        if (bundleEntrySearchComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, BundleEntrySearchComponent.class.getSimpleName(), bundleEntrySearchComponent)
            );
        }

        return bundleEntrySearchComponentOp.get();
    }

    public BundleEntrySearchComponent create(@NotNull BundleEntrySearchComponent newBundleEntrySearchComponent) {
        checkForUniqueness(newBundleEntrySearchComponent, repository);
        newBundleEntrySearchComponent.setId(null);
        return repository.save(newBundleEntrySearchComponent);
    }

    public BundleEntrySearchComponent update(UUID bundleEntrySearchComponent, @NotNull BundleEntrySearchComponent update) throws ResourceNotFoundException {
        getOne(bundleEntrySearchComponent); // to check if the update exist
        update.setId(bundleEntrySearchComponent);
        return repository.save(update);
    }

    public void delete(UUID bundleEntrySearchComponent) {
        getOne(bundleEntrySearchComponent);
        repository.deleteById(bundleEntrySearchComponent);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

}
