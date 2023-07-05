package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import de.rki.demis.fhir.exception.ResourceBadRequestException;
>>>>>>> acf3b2c (wip)
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
import de.rki.demis.fhir.model.BundleEntrySearchComponent;
import de.rki.demis.fhir.repository.BundleEntrySearchComponentRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
=======
>>>>>>> acf3b2c (wip)
=======
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 694cae4 (some refactorings are done)
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
=======
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntrySearchComponentService implements BaseService<BundleEntrySearchComponent> {
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    private final BundleEntrySearchComponentRepository repository;


    public List<BundleEntrySearchComponent> listAll() {
        return repository.findAll();
    }

    public BundleEntrySearchComponent getOne(UUID bundleEntrySearchComponent) {
        Optional<BundleEntrySearchComponent> bundleEntrySearchComponentOp = repository.findById(bundleEntrySearchComponent);

        if (bundleEntrySearchComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
<<<<<<< HEAD
<<<<<<< HEAD
                    String.format(NOT_EXIST_MSG, BundleEntrySearchComponent.class.getSimpleName(), bundleEntrySearchComponent)
=======
                    String.format("::: A BundleEntrySearchComponent with 'id = %s' does not exist :::", bundleEntrySearchComponent)
>>>>>>> acf3b2c (wip)
=======
                    String.format(NOT_EXIST_MSG, BundleEntrySearchComponent.class.getSimpleName(), bundleEntrySearchComponent)
>>>>>>> 694cae4 (some refactorings are done)
            );
        }

        return bundleEntrySearchComponentOp.get();
    }

    public BundleEntrySearchComponent create(@NotNull BundleEntrySearchComponent newBundleEntrySearchComponent) {
<<<<<<< HEAD
<<<<<<< HEAD
        checkForUniqueness(newBundleEntrySearchComponent, repository);
=======
>>>>>>> acf3b2c (wip)
=======
        checkForUniqueness(newBundleEntrySearchComponent, repository);
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        newBundleEntrySearchComponent.setId(null);
        return repository.save(newBundleEntrySearchComponent);
    }

<<<<<<< HEAD
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
=======
    public BundleEntrySearchComponent update(UUID bundleEntrySearchComponent, @NotNull BundleEntrySearchComponent update) throws ResourceNotFoundException {
        getOne(bundleEntrySearchComponent); // to check if the update exist
        update.setId(bundleEntrySearchComponent);
        return repository.save(update);
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    }

    public void delete(UUID bundleEntrySearchComponent) {
        getOne(bundleEntrySearchComponent);
        repository.deleteById(bundleEntrySearchComponent);
    }

<<<<<<< HEAD
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
=======
    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    }

}
