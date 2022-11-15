package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.BinaryMod;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface BinaryRepository extends CassandraRepository<BinaryMod, UUID> {
    boolean existsById(@NotNull UUID id);
}
