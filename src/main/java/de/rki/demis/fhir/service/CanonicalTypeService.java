package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.CanonicalType;
import de.rki.demis.fhir.repository.CanonicalTypeRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CanonicalTypeService implements BaseService<CanonicalType> {
    private final CanonicalTypeRepository repository;


    public List<CanonicalType> listAll() {
        return repository.findAll();
    }

    public CanonicalType getOne(UUID canonicalTypeId) {
        Optional<CanonicalType> canonicalTypeOp = repository.findById(canonicalTypeId);

        if (canonicalTypeOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, CanonicalType.class.getSimpleName(), canonicalTypeId)
            );
        }

        return canonicalTypeOp.get();
    }

    public CanonicalType create(@NotNull CanonicalType newCanonicalType) {
        checkForUniqueness(newCanonicalType, repository);
        newCanonicalType.setId(null);
        return repository.save(newCanonicalType);
    }

    public CanonicalType update(UUID canonicalTypeId, @NotNull CanonicalType update) throws ResourceNotFoundException {
        getOne(canonicalTypeId); // to check if the update exist
        update.setId(canonicalTypeId);
        return repository.save(update);
    }

    public void delete(UUID canonicalTypeId) {
        getOne(canonicalTypeId);
        repository.deleteById(canonicalTypeId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

}
