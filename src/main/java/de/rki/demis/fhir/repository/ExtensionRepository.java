package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Extension;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ExtensionRepository
        extends JpaRepository<Extension, UUID>, JpaSpecificationExecutor<Extension> {

    boolean existsById(@NotNull UUID id);

}
