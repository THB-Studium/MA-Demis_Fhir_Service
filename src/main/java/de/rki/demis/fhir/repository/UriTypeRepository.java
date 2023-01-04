package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.UriType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UriTypeRepository extends CrudRepository<UriType, UUID> {
    boolean existsById(@NotNull UUID id);
}
