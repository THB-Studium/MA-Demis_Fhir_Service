package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
=======
import de.rki.demis.fhir.exception.ResourceBadRequestException;
>>>>>>> acf3b2c (wip)
import de.rki.demis.fhir.model.BundleEntryResponseComponent;
import de.rki.demis.fhir.repository.BundleEntryResponseComponentRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
=======
>>>>>>> acf3b2c (wip)
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

<<<<<<< HEAD
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntryResponseComponentService implements BaseService<BundleEntryResponseComponent> {
=======
@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntryResponseComponentService {
>>>>>>> acf3b2c (wip)
    private final BundleEntryResponseComponentRepository repository;


    public List<BundleEntryResponseComponent> listAll() {
        return repository.findAll();
    }

    public BundleEntryResponseComponent getOne(UUID bundleEntryResponseComponentId) {
        Optional<BundleEntryResponseComponent> bundleEntryResponseComponentOp = repository.findById(bundleEntryResponseComponentId);

        if (bundleEntryResponseComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
<<<<<<< HEAD
                    String.format(NOT_EXIST_MSG, BundleEntryResponseComponent.class.getSimpleName(), bundleEntryResponseComponentId)
=======
                    String.format("::: A BundleEntryResponseComponent with 'id = %s' does not exist :::", bundleEntryResponseComponentId)
>>>>>>> acf3b2c (wip)
            );
        }

        return bundleEntryResponseComponentOp.get();
    }

    public BundleEntryResponseComponent create(@NotNull BundleEntryResponseComponent newBundleEntryResponseComponent) {
<<<<<<< HEAD
        checkForUniqueness(newBundleEntryResponseComponent, repository);
=======
>>>>>>> acf3b2c (wip)
        newBundleEntryResponseComponent.setId(null);
        return repository.save(newBundleEntryResponseComponent);
    }

<<<<<<< HEAD
    public BundleEntryResponseComponent update(UUID bundleEntryResponseComponentId, @NotNull BundleEntryResponseComponent update) throws ResourceNotFoundException {
        getOne(bundleEntryResponseComponentId); // to check if the update exist
        update.setId(bundleEntryResponseComponentId);
        return repository.save(update);
=======
    public void update(UUID bundleEntryResponseComponentId, @NotNull BundleEntryResponseComponent update) throws ResourceNotFoundException {
        getOne(bundleEntryResponseComponentId);

        if (!bundleEntryResponseComponentId.equals(update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(bundleEntryResponseComponentId);
        repository.save(update);
>>>>>>> acf3b2c (wip)
    }

    public void delete(UUID bundleEntryResponseComponentId) {
        getOne(bundleEntryResponseComponentId);
        repository.deleteById(bundleEntryResponseComponentId);
    }

<<<<<<< HEAD
    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
=======
    private void checkForUniqueness(@NotNull BundleEntryResponseComponent bundleEntryResponseComponent) {
        if (repository.existsById(bundleEntryResponseComponent.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BundleEntryResponseComponent with the id=%s already exist :::", bundleEntryResponseComponent.getId())
            );
        }
>>>>>>> acf3b2c (wip)
    }

}
