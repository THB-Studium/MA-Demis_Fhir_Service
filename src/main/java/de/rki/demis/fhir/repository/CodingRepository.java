package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Coding;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CodingRepository extends CrudRepository<Coding, UUID> {
    boolean existsById(@NotNull UUID id);
}
