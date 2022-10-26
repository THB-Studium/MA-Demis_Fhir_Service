package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.BundleMod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BundleRepository
        extends JpaRepository<BundleMod, UUID>, JpaSpecificationExecutor<BundleMod> {

    boolean existsById(UUID id);

}
