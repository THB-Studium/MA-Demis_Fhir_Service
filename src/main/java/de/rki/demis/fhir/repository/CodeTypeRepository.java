package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.CodeType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CodeTypeRepository extends CrudRepository<CodeType, UUID> {
    boolean existsById(@NotNull UUID id);
}
