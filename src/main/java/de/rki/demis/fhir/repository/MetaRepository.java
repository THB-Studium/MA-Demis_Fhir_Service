package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Meta;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface MetaRepository extends CassandraRepository<Meta, UUID> {
    @AllowFiltering
    boolean existsById(@NotNull UUID id);
}
