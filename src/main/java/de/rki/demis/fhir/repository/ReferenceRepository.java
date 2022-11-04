package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Reference;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ReferenceRepository
        extends JpaRepository<Reference, UUID>, JpaSpecificationExecutor<Reference> {

    boolean existsById(@NotNull UUID id);

}
