package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Enumeration;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface EnumerationRepository extends JpaRepository<Enumeration, UUID>, JpaSpecificationExecutor<Enumeration> {

    boolean existsById(@NotNull UUID id);

}
