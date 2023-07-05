package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.BundleEntryResponseComponent;
import de.rki.demis.fhir.repository.BundleEntryResponseComponentRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntryResponseComponentService implements BaseService<BundleEntryResponseComponent> {
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
        checkForUniqueness(newBundleEntryResponseComponent, repository);
        newBundleEntryResponseComponent.setId(null);
        return repository.save(newBundleEntryResponseComponent);
    }

    public BundleEntryResponseComponent update(UUID bundleEntryResponseComponentId, @NotNull BundleEntryResponseComponent update) throws ResourceNotFoundException {
        getOne(bundleEntryResponseComponentId); // to check if the update exist
        update.setId(bundleEntryResponseComponentId);
        return repository.save(update);
    }

    public void delete(UUID bundleEntryResponseComponentId) {
        getOne(bundleEntryResponseComponentId);
        repository.deleteById(bundleEntryResponseComponentId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

}
