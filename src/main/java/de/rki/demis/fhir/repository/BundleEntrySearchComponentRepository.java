package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.BundleEntrySearchComponent;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BundleEntrySearchComponentRepository
        extends JpaRepository<BundleEntrySearchComponent, UUID>,
        JpaSpecificationExecutor<BundleEntrySearchComponent> {

    boolean existsById(@NotNull UUID id);
}
