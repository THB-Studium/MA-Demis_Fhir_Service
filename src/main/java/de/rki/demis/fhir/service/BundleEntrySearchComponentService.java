package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
=======
import de.rki.demis.fhir.exception.ResourceBadRequestException;
>>>>>>> acf3b2c (wip)
import de.rki.demis.fhir.model.BundleEntrySearchComponent;
import de.rki.demis.fhir.repository.BundleEntrySearchComponentRepository;
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
public class BundleEntrySearchComponentService implements BaseService<BundleEntrySearchComponent> {
=======
@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntrySearchComponentService {
>>>>>>> acf3b2c (wip)
    private final BundleEntrySearchComponentRepository repository;


    public List<BundleEntrySearchComponent> listAll() {
        return repository.findAll();
    }

    public BundleEntrySearchComponent getOne(UUID bundleEntrySearchComponent) {
        Optional<BundleEntrySearchComponent> bundleEntrySearchComponentOp = repository.findById(bundleEntrySearchComponent);

        if (bundleEntrySearchComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
<<<<<<< HEAD
                    String.format(NOT_EXIST_MSG, BundleEntrySearchComponent.class.getSimpleName(), bundleEntrySearchComponent)
=======
                    String.format("::: A BundleEntrySearchComponent with 'id = %s' does not exist :::", bundleEntrySearchComponent)
>>>>>>> acf3b2c (wip)
            );
        }

        return bundleEntrySearchComponentOp.get();
    }

    public BundleEntrySearchComponent create(@NotNull BundleEntrySearchComponent newBundleEntrySearchComponent) {
<<<<<<< HEAD
        checkForUniqueness(newBundleEntrySearchComponent, repository);
=======
>>>>>>> acf3b2c (wip)
        newBundleEntrySearchComponent.setId(null);
        return repository.save(newBundleEntrySearchComponent);
    }

<<<<<<< HEAD
    public BundleEntrySearchComponent update(UUID bundleEntrySearchComponent, @NotNull BundleEntrySearchComponent update) throws ResourceNotFoundException {
        getOne(bundleEntrySearchComponent); // to check if the update exist
        update.setId(bundleEntrySearchComponent);
        return repository.save(update);
=======
    public void update(UUID bundleEntrySearchComponent, @NotNull BundleEntrySearchComponent update) throws ResourceNotFoundException {
        getOne(bundleEntrySearchComponent);

        if (!bundleEntrySearchComponent.equals(update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(bundleEntrySearchComponent);
        repository.save(update);
>>>>>>> acf3b2c (wip)
    }

    public void delete(UUID bundleEntrySearchComponent) {
        getOne(bundleEntrySearchComponent);
        repository.deleteById(bundleEntrySearchComponent);
    }

<<<<<<< HEAD
    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
=======
    private void checkForUniqueness(@NotNull BundleEntrySearchComponent bundleEntrySearchComponent) {
        if (repository.existsById(bundleEntrySearchComponent.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BundleEntrySearchComponent with the id=%s already exist :::", bundleEntrySearchComponent.getId())
            );
        }
>>>>>>> acf3b2c (wip)
    }

}
