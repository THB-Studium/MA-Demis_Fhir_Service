package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.BinaryMod;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface BinaryRepository
        extends JpaRepository<BinaryMod, UUID>, JpaSpecificationExecutor<BinaryMod> {

    boolean existsById(@NotNull UUID id);

}
