package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
import de.rki.demis.fhir.exception.ResourceBadRequestException;
>>>>>>> acf3b2c (wip)
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
=======
import de.rki.demis.fhir.model.BundleEntryComponent;
>>>>>>> 694cae4 (some refactorings are done)
import de.rki.demis.fhir.model.BundleEntryRequestComponent;
import de.rki.demis.fhir.repository.BundleEntryRequestComponentRepository;
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
public class BundleEntryRequestComponentService implements BaseService<BundleEntryRequestComponent> {
=======
@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntryRequestComponentService {
>>>>>>> acf3b2c (wip)
=======
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntryRequestComponentService implements BaseService<BundleEntryRequestComponent> {
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    private final BundleEntryRequestComponentRepository repository;


    public List<BundleEntryRequestComponent> listAll() {
        return repository.findAll();
    }

    public BundleEntryRequestComponent getOne(UUID bundleEntryRequestComponentId) {
        Optional<BundleEntryRequestComponent> bundleEntryRequestComponentOp = repository.findById(bundleEntryRequestComponentId);

        if (bundleEntryRequestComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
<<<<<<< HEAD
<<<<<<< HEAD
                    String.format(NOT_EXIST_MSG, BundleEntryRequestComponent.class.getSimpleName(), bundleEntryRequestComponentId)
=======
                    String.format("::: A BundleEntryRequestComponent with 'id = %s' does not exist :::", bundleEntryRequestComponentId)
>>>>>>> acf3b2c (wip)
=======
                    String.format(NOT_EXIST_MSG, BundleEntryRequestComponent.class.getSimpleName(), bundleEntryRequestComponentId)
>>>>>>> 694cae4 (some refactorings are done)
            );
        }

        return bundleEntryRequestComponentOp.get();
    }

    public BundleEntryRequestComponent create(@NotNull BundleEntryRequestComponent newBundleEntryRequestComponent) {
<<<<<<< HEAD
<<<<<<< HEAD
        checkForUniqueness(newBundleEntryRequestComponent, repository);
=======
>>>>>>> acf3b2c (wip)
=======
        checkForUniqueness(newBundleEntryRequestComponent, repository);
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        newBundleEntryRequestComponent.setId(null);
        return repository.save(newBundleEntryRequestComponent);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public BundleEntryRequestComponent update(UUID bundleEntryRequestComponentId, @NotNull BundleEntryRequestComponent update) throws ResourceNotFoundException {
        getOne(bundleEntryRequestComponentId);
        update.setId(bundleEntryRequestComponentId);
        return repository.save(update);
=======
    public void update(UUID bundleEntryRequestComponentId, @NotNull BundleEntryRequestComponent update) throws ResourceNotFoundException {
=======
    public BundleEntryRequestComponent update(UUID bundleEntryRequestComponentId, @NotNull BundleEntryRequestComponent update) throws ResourceNotFoundException {
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        getOne(bundleEntryRequestComponentId);
        update.setId(bundleEntryRequestComponentId);
<<<<<<< HEAD
        repository.save(update);
>>>>>>> acf3b2c (wip)
=======
        return repository.save(update);
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    }

    public void delete(UUID bundleEntryRequestComponentId) {
        getOne(bundleEntryRequestComponentId);
        repository.deleteById(bundleEntryRequestComponentId);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
=======
    private void checkForUniqueness(@NotNull BundleEntryRequestComponent bundleEntryRequestComponent) {
        if (repository.existsById(bundleEntryRequestComponent.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BundleEntryRequestComponent with the id=%s already exist :::", bundleEntryRequestComponent.getId())
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
