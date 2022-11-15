package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Coding;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface CodingRepository extends CassandraRepository<Coding, UUID> {
    boolean existsById(@NotNull UUID id);
}
