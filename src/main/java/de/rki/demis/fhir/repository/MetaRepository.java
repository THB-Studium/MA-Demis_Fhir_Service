package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Meta;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MetaRepository extends CrudRepository<Meta, UUID> {
    boolean existsById(@NotNull UUID id);
}
