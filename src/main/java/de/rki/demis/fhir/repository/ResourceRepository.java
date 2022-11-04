package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.util.fhir_object.classes.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ResourceRepository
        extends JpaRepository<Resource, UUID>, JpaSpecificationExecutor<Resource> {

    boolean existsById(@NotNull UUID id);

}
