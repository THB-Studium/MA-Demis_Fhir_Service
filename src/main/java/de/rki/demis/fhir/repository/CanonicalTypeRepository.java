package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.CanonicalType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CanonicalTypeRepository extends CrudRepository<CanonicalType, UUID> {
    boolean existsById(@NotNull UUID id);
}
