package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Identifier;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface IdentifierRepository
        extends JpaRepository<Identifier, UUID>, JpaSpecificationExecutor<Identifier> {

    boolean existsById(@NotNull UUID id);

}
