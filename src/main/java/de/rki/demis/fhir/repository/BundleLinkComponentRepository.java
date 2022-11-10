package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.BundleLinkComponent;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BundleLinkComponentRepository
        extends JpaRepository<BundleLinkComponent, UUID>, JpaSpecificationExecutor<BundleLinkComponent> {

    boolean existsById(@NotNull UUID id);

}
