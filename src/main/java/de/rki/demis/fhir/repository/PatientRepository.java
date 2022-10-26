package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.PatientMod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface PatientRepository
        extends JpaRepository<PatientMod, UUID>, JpaSpecificationExecutor<PatientMod> {

}
