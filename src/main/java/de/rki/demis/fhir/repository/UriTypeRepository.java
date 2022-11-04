package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.UriType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UriTypeRepository
        extends JpaRepository<UriType, UUID>, JpaSpecificationExecutor<UriType> {

    boolean existsById(@NotNull UUID id);

}
