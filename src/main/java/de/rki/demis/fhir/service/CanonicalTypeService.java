package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import de.rki.demis.fhir.exception.ResourceBadRequestException;
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
import de.rki.demis.fhir.model.CanonicalType;
import de.rki.demis.fhir.repository.CanonicalTypeRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
=======
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Optional;
import java.util.UUID;

import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CanonicalTypeService implements BaseService<CanonicalType> {
=======
import java.util.Objects;
=======
>>>>>>> acf3b2c (wip)
import java.util.Optional;
import java.util.UUID;

import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
<<<<<<< HEAD
public class CanonicalTypeService {
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
public class CanonicalTypeService implements BaseService<CanonicalType> {
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    private final CanonicalTypeRepository repository;


    public List<CanonicalType> listAll() {
        return repository.findAll();
    }

    public CanonicalType getOne(UUID canonicalTypeId) {
        Optional<CanonicalType> canonicalTypeOp = repository.findById(canonicalTypeId);

        if (canonicalTypeOp.isEmpty()) {
            throw new ResourceNotFoundException(
<<<<<<< HEAD
<<<<<<< HEAD
                    String.format(NOT_EXIST_MSG, CanonicalType.class.getSimpleName(), canonicalTypeId)
=======
                    String.format("::: A CanonicalType with 'id = %s' does not exist :::", canonicalTypeId)
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
                    String.format(NOT_EXIST_MSG, CanonicalType.class.getSimpleName(), canonicalTypeId)
>>>>>>> 694cae4 (some refactorings are done)
            );
        }

        return canonicalTypeOp.get();
    }

    public CanonicalType create(@NotNull CanonicalType newCanonicalType) {
<<<<<<< HEAD
<<<<<<< HEAD
        checkForUniqueness(newCanonicalType, repository);
=======
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
        checkForUniqueness(newCanonicalType, repository);
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        newCanonicalType.setId(null);
        return repository.save(newCanonicalType);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public CanonicalType update(UUID canonicalTypeId, @NotNull CanonicalType update) throws ResourceNotFoundException {
        getOne(canonicalTypeId); // to check if the update exist
        update.setId(canonicalTypeId);
        return repository.save(update);
=======
    public void update(UUID canonicalTypeId, @NotNull CanonicalType update) throws ResourceNotFoundException {
        getOne(canonicalTypeId);

        if (!canonicalTypeId.equals(update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(canonicalTypeId);
        repository.save(update);
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
    public CanonicalType update(UUID canonicalTypeId, @NotNull CanonicalType update) throws ResourceNotFoundException {
        getOne(canonicalTypeId); // to check if the update exist
        update.setId(canonicalTypeId);
        return repository.save(update);
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    }

    public void delete(UUID canonicalTypeId) {
        getOne(canonicalTypeId);
        repository.deleteById(canonicalTypeId);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
=======
    private void checkForUniqueness(@NotNull CanonicalType canonicalType) {
        if (repository.existsById(canonicalType.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A CanonicalType with the id=%s already exist :::", canonicalType.getId())
            );
        }
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    }

}
