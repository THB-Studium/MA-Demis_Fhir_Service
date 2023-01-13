package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ResourceRepository extends CassandraRepository<Resource, UUID> {
    @AllowFiltering
    boolean existsById(@NotNull UUID id);
}
