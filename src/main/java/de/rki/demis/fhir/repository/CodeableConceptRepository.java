package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.CodeableConcept;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CodeableConceptRepository
        extends JpaRepository<CodeableConcept, UUID>, JpaSpecificationExecutor<CodeableConcept> {

    boolean existsById(@NotNull UUID id);

}
