package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.BinaryMod;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BinaryRepository extends CrudRepository<BinaryMod, UUID> {
    boolean existsById(@NotNull UUID id);
}
