package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.BundleEntryResponseComponent;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BundleEntryResponseComponentRepository
        extends JpaRepository<BundleEntryResponseComponent, UUID>,
        JpaSpecificationExecutor<BundleEntryResponseComponent> {

    boolean existsById(@NotNull UUID id);
}
