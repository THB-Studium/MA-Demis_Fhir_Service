package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.CodeableConcept;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface CodeableConceptRepository extends CassandraRepository<CodeableConcept, UUID> {
    boolean existsById(@NotNull UUID id);
}
