package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.BundleEntryComponent;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BundleEntryComponentRepository
        extends JpaRepository<BundleEntryComponent, UUID>, JpaSpecificationExecutor<BundleEntryComponent> {

    boolean existsById(@NotNull UUID id);

}
