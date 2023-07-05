package de.rki.demis.fhir.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BaseService<T> {
    List<T> listAll();

    T getOne(UUID id);

    T create(T newBundle);

    T update(UUID id, T update);

    void delete(UUID id);

    JpaRepository<?, UUID> getRepository();
}

