package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.BundleEntryRequestComponent;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BundleEntryRequestComponentRepository
        extends JpaRepository<BundleEntryRequestComponent, UUID>,
        JpaSpecificationExecutor<BundleEntryRequestComponent> {

    boolean existsById(@NotNull UUID id);
}
