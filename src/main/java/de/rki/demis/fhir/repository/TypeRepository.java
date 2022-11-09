package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Type;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TypeRepository
        extends JpaRepository<Type, UUID>, JpaSpecificationExecutor<Type> {

    boolean existsById(@NotNull UUID id);

}
