package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import de.rki.demis.fhir.exception.ResourceBadRequestException;
>>>>>>> acf3b2c (wip)
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
import de.rki.demis.fhir.model.Enumeration;
import de.rki.demis.fhir.repository.EnumerationBundleTypeRepository;
import de.rki.demis.fhir.util.fhir_object.enums.BundleType;
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
public class EnumerationBundleTypeService implements BaseService<Enumeration<BundleType>> {
=======
@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class EnumerationBundleTypeService {
>>>>>>> acf3b2c (wip)
=======
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class EnumerationBundleTypeService implements BaseService<Enumeration<BundleType>> {
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    private final EnumerationBundleTypeRepository repository;


    public List<Enumeration<BundleType>> listAll() {
        return repository.findAll();
    }

    public Enumeration<BundleType> getOne(UUID enumerationId) {
        Optional<Enumeration<BundleType>> enumerationOp = repository.findById(enumerationId);

        if (enumerationOp.isEmpty()) {
            throw new ResourceNotFoundException(
<<<<<<< HEAD
<<<<<<< HEAD
                    String.format(NOT_EXIST_MSG, "Enumeration-" + BundleType.class.getSimpleName(), enumerationId)
=======
                    String.format("::: A Enumeration Bundle Type with 'id = %s' does not exist :::", enumerationId)
>>>>>>> acf3b2c (wip)
=======
                    String.format(NOT_EXIST_MSG, "Enumeration-" + BundleType.class.getSimpleName(), enumerationId)
>>>>>>> 694cae4 (some refactorings are done)
            );
        }

        return enumerationOp.get();
    }

    public Enumeration<BundleType> create(@NotNull Enumeration<BundleType> newEnumeration) {
<<<<<<< HEAD
<<<<<<< HEAD
        checkForUniqueness(newEnumeration, repository);
=======
>>>>>>> acf3b2c (wip)
=======
        checkForUniqueness(newEnumeration, repository);
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        newEnumeration.setId(null);
        return repository.save(newEnumeration);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public Enumeration<BundleType> update(UUID enumerationId, @NotNull Enumeration<BundleType> update) throws ResourceNotFoundException {
        getOne(enumerationId);// to check if the update exist
        update.setId(enumerationId);
        return repository.save(update);
=======
    public void update(UUID enumerationId, @NotNull Enumeration<BundleType> update) throws ResourceNotFoundException {
        getOne(enumerationId);

        if (!enumerationId.equals(update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(enumerationId);
        repository.save(update);
>>>>>>> acf3b2c (wip)
=======
    public Enumeration<BundleType> update(UUID enumerationId, @NotNull Enumeration<BundleType> update) throws ResourceNotFoundException {
        getOne(enumerationId);// to check if the update exist
        update.setId(enumerationId);
        return repository.save(update);
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    }

    public void delete(UUID enumerationId) {
        getOne(enumerationId);
        repository.deleteById(enumerationId);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
=======
    private void checkForUniqueness(@NotNull Enumeration<BundleType> enumeration) {
        if (repository.existsById(enumeration.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Enumeration Bundle Type with the id=%s already exist :::", enumeration.getId())
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
