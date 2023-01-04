package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Reference;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReferenceRepository extends CrudRepository<Reference, UUID> {
    boolean existsById(@NotNull UUID id);
}
