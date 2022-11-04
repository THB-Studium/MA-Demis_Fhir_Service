package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Coding;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CodingRepository
        extends JpaRepository<Coding, UUID>, JpaSpecificationExecutor<Coding> {

    boolean existsById(@NotNull UUID id);

}
