package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Type;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface TypeRepository extends CassandraRepository<Type, UUID> {
    @AllowFiltering
    boolean existsById(@NotNull UUID id);
}
