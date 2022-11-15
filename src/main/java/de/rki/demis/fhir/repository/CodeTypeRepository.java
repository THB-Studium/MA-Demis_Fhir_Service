package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.CodeType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface CodeTypeRepository extends CassandraRepository<CodeType, UUID> {
    boolean existsById(@NotNull UUID id);
}
