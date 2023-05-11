package de.rki.demis.fhir.repository;

import de.rki.demis.fhir.model.Enumeration;
import de.rki.demis.fhir.util.fhir_object.enums.BundleType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface EnumerationBundleTypeRepository extends JpaRepository<Enumeration<BundleType>, UUID>, JpaSpecificationExecutor<Enumeration<BundleType>> {

    boolean existsById(@NotNull UUID id);

}
