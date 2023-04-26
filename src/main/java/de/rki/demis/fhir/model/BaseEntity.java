package de.rki.demis.fhir.model;

import java.util.UUID;


public interface BaseEntity {
    UUID getId();

    void setId(UUID id);
}
