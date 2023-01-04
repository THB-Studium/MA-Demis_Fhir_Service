package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Extension;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ExtensionRepository extends CrudRepository<Extension, UUID> {
    boolean existsById(@NotNull UUID id);
}
