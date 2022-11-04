package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.CanonicalType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CanonicalTypeRepository
        extends JpaRepository<CanonicalType, UUID>, JpaSpecificationExecutor<CanonicalType> {

    boolean existsById(@NotNull UUID id);

}
