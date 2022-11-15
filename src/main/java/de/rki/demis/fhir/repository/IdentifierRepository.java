package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Identifier;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface IdentifierRepository extends CassandraRepository<Identifier, UUID> {
    boolean existsById(@NotNull UUID id);
}
