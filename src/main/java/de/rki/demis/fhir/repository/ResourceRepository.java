package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ResourceRepository extends CrudRepository<Resource, UUID> {
    boolean existsById(@NotNull UUID id);
}
