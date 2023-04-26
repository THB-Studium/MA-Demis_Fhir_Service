package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
=======
import de.rki.demis.fhir.exception.ResourceBadRequestException;
>>>>>>> c598496 (update issues in BinaryService fixed)
import de.rki.demis.fhir.model.CanonicalType;
import de.rki.demis.fhir.repository.CanonicalTypeRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
=======
>>>>>>> c598496 (update issues in BinaryService fixed)
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CanonicalTypeService {
>>>>>>> c598496 (update issues in BinaryService fixed)
    private final CanonicalTypeRepository repository;


    public List<CanonicalType> listAll() {
        return repository.findAll();
    }

    public CanonicalType getOne(UUID canonicalTypeId) {
        Optional<CanonicalType> canonicalTypeOp = repository.findById(canonicalTypeId);

        if (canonicalTypeOp.isEmpty()) {
            throw new ResourceNotFoundException(
<<<<<<< HEAD
                    String.format(NOT_EXIST_MSG, CanonicalType.class.getSimpleName(), canonicalTypeId)
=======
                    String.format("::: A CanonicalType with 'id = %s' does not exist :::", canonicalTypeId)
>>>>>>> c598496 (update issues in BinaryService fixed)
            );
        }

        return canonicalTypeOp.get();
    }

    public CanonicalType create(@NotNull CanonicalType newCanonicalType) {
<<<<<<< HEAD
        checkForUniqueness(newCanonicalType, repository);
=======
>>>>>>> c598496 (update issues in BinaryService fixed)
        newCanonicalType.setId(null);
        return repository.save(newCanonicalType);
    }

<<<<<<< HEAD
    public CanonicalType update(UUID canonicalTypeId, @NotNull CanonicalType update) throws ResourceNotFoundException {
        getOne(canonicalTypeId); // to check if the update exist
        update.setId(canonicalTypeId);
        return repository.save(update);
=======
    public void update(UUID canonicalTypeId, @NotNull CanonicalType update) throws ResourceNotFoundException {
        getOne(canonicalTypeId);

        if (!Objects.equals(canonicalTypeId, update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(canonicalTypeId);
        repository.save(update);
>>>>>>> c598496 (update issues in BinaryService fixed)
    }

    public void delete(UUID canonicalTypeId) {
        getOne(canonicalTypeId);
        repository.deleteById(canonicalTypeId);
    }

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
    }

}
