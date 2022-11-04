package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.CodeType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CodeTypeRepository
        extends JpaRepository<CodeType, UUID>, JpaSpecificationExecutor<CodeType> {

    boolean existsById(@NotNull UUID id);

}
