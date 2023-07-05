package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import de.rki.demis.fhir.exception.ResourceBadRequestException;
>>>>>>> acf3b2c (wip)
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
import de.rki.demis.fhir.model.BundleEntryResponseComponent;
import de.rki.demis.fhir.repository.BundleEntryResponseComponentRepository;
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
=======
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleEntryResponseComponentService implements BaseService<BundleEntryResponseComponent> {
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
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
<<<<<<< HEAD
        checkForUniqueness(newBundleEntryResponseComponent, repository);
=======
>>>>>>> acf3b2c (wip)
=======
        checkForUniqueness(newBundleEntryResponseComponent, repository);
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        newBundleEntryResponseComponent.setId(null);
        return repository.save(newBundleEntryResponseComponent);
    }

<<<<<<< HEAD
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
=======
    public BundleEntryResponseComponent update(UUID bundleEntryResponseComponentId, @NotNull BundleEntryResponseComponent update) throws ResourceNotFoundException {
        getOne(bundleEntryResponseComponentId); // to check if the update exist
        update.setId(bundleEntryResponseComponentId);
        return repository.save(update);
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    }

    public void delete(UUID bundleEntryResponseComponentId) {
        getOne(bundleEntryResponseComponentId);
        repository.deleteById(bundleEntryResponseComponentId);
    }

<<<<<<< HEAD
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
=======
    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    }

}
