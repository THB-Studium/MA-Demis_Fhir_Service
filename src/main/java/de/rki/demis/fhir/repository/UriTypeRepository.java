package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.UriType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface UriTypeRepository extends CassandraRepository<UriType, UUID> {
    boolean existsById(@NotNull UUID id);
}
