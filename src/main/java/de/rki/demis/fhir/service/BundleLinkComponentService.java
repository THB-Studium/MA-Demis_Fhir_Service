package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.BundleLinkComponent;
import de.rki.demis.fhir.repository.BundleLinkComponentRepository;
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
public class BundleLinkComponentService {
    private final BundleLinkComponentRepository repository;


    public List<BundleLinkComponent> listAll() {
        return repository.findAll();
    }

    public BundleLinkComponent getOne(UUID bundleLinkComponentId) {
        Optional<BundleLinkComponent> bundleLinkComponentOp = repository.findById(bundleLinkComponentId);

        if (bundleLinkComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A BundleLinkComponent with 'id = %s' does not exist :::", bundleLinkComponentId)
            );
        }

        return bundleLinkComponentOp.get();
    }

    public BundleLinkComponent create(@NotNull BundleLinkComponent newBundleLinkComponent) {
        newBundleLinkComponent.setId(null);
        return repository.save(newBundleLinkComponent);
    }

    public void update(UUID bundleLinkComponentId, @NotNull BundleLinkComponent update)
            throws ResourceNotFoundException {
        getOne(bundleLinkComponentId);

        if (!bundleLinkComponentId.equals(update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(bundleLinkComponentId);
        repository.save(update);
    }

    public void delete(UUID bundleLinkComponentId) {
        getOne(bundleLinkComponentId);
        repository.deleteById(bundleLinkComponentId);
    }

    private void checkForUniqueness(@NotNull BundleLinkComponent bundleLinkComponent) {
        if (repository.existsById(bundleLinkComponent.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BundleLinkComponent with the id=%s already exist :::", bundleLinkComponent.getId())
            );
        }
    }

}
