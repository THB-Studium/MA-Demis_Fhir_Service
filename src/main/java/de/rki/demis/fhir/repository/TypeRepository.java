package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Type;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TypeRepository extends CrudRepository<Type, UUID> {
    boolean existsById(@NotNull UUID id);
}
