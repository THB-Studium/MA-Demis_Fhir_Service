package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.CanonicalType;
import de.rki.demis.fhir.repository.CanonicalTypeRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CanonicalTypeService {
    private final CanonicalTypeRepository repository;


    public List<CanonicalType> listAll() {
        return repository.findAll();
    }

    public CanonicalType getOne(UUID canonicalTypeId) {
        Optional<CanonicalType> canonicalTypeOp = repository.findById(canonicalTypeId);

        if (canonicalTypeOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A CanonicalType with 'id = %s' does not exist :::", canonicalTypeId)
            );
        }

        return canonicalTypeOp.get();
    }

    public CanonicalType create(@NotNull CanonicalType newCanonicalType) {
        newCanonicalType.setId(null);
        return repository.save(newCanonicalType);
    }

    public void update(UUID canonicalTypeId, @NotNull CanonicalType update) throws ResourceNotFoundException {
        getOne(canonicalTypeId);

        if (!Objects.equals(canonicalTypeId, update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(canonicalTypeId);
        repository.save(update);
    }

    public void delete(UUID canonicalTypeId) {
        getOne(canonicalTypeId);
        repository.deleteById(canonicalTypeId);
    }

    private void checkForUniqueness(@NotNull CanonicalType canonicalType) {
        if (repository.existsById(canonicalType.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A CanonicalType with the id=%s already exist :::", canonicalType.getId())
            );
        }
    }

}
