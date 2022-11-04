package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Base64BinaryType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface Base64BinaryTypeRepository
        extends JpaRepository<Base64BinaryType, UUID>, JpaSpecificationExecutor<Base64BinaryType> {

    boolean existsById(@NotNull UUID id);

}
