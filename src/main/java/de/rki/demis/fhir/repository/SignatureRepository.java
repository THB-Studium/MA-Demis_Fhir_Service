package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Signature;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SignatureRepository
        extends JpaRepository<Signature, UUID>, JpaSpecificationExecutor<Signature> {

    boolean existsById(@NotNull UUID id);

}
