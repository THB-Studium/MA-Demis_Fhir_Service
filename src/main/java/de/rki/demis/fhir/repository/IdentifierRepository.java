package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Identifier;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IdentifierRepository extends CrudRepository<Identifier, UUID> {
    boolean existsById(@NotNull UUID id);
}
