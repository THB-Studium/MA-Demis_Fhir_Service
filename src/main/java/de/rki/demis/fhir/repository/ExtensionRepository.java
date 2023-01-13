package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Extension;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ExtensionRepository extends CassandraRepository<Extension, UUID> {
    @AllowFiltering
    boolean existsById(@NotNull UUID id);
}
