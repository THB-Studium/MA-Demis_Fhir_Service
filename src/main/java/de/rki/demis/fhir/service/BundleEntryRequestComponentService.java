package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.BundleEntryRequestComponent;
import de.rki.demis.fhir.repository.BundleEntryRequestComponentRepository;
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
public class BundleEntryRequestComponentService implements BaseService<BundleEntryRequestComponent> {
    private final BundleEntryRequestComponentRepository repository;


    public List<BundleEntryRequestComponent> listAll() {
        return repository.findAll();
    }

    public BundleEntryRequestComponent getOne(UUID bundleEntryRequestComponentId) {
        Optional<BundleEntryRequestComponent> bundleEntryRequestComponentOp = repository.findById(bundleEntryRequestComponentId);

        if (bundleEntryRequestComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, BundleEntryRequestComponent.class.getSimpleName(), bundleEntryRequestComponentId)
            );
        }

        return bundleEntryRequestComponentOp.get();
    }

    public BundleEntryRequestComponent create(@NotNull BundleEntryRequestComponent newBundleEntryRequestComponent) {
        checkForUniqueness(newBundleEntryRequestComponent, repository);
        newBundleEntryRequestComponent.setId(null);
        return repository.save(newBundleEntryRequestComponent);
    }

    public BundleEntryRequestComponent update(UUID bundleEntryRequestComponentId, @NotNull BundleEntryRequestComponent update) throws ResourceNotFoundException {
        getOne(bundleEntryRequestComponentId);
        update.setId(bundleEntryRequestComponentId);
        return repository.save(update);
    }

    public void delete(UUID bundleEntryRequestComponentId) {
        getOne(bundleEntryRequestComponentId);
        repository.deleteById(bundleEntryRequestComponentId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

}
