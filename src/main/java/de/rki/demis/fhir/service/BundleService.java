package de.rki.demis.fhir.service;

import de.rki.demis.fhir.exception.ParsingException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.exception.ResourceNotFoundException;
import de.rki.demis.fhir.model.table.BundleMod;
import de.rki.demis.fhir.repository.BundleRepository;
import de.rki.demis.fhir.search.criteria.BundleCriteria;
import de.rki.demis.fhir.transfert.bundle.Bundle2BundleMod;
import de.rki.demis.fhir.util.service.FhirParserService;
import lombok.RequiredArgsConstructor;
import org.hl7.fhir.r4.model.Bundle;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BundleService {
    private final BundleRepository repository;
    private final FhirParserService fhirParserService;

    public List<BundleMod> listAll() {
        return repository.findAll();
    }

    public BundleMod getOne(UUID bundleId) {
        Optional<BundleMod> bundleOp = repository.findById(bundleId);

        if (bundleOp.isEmpty()) {
            throw new ResourceNotFoundException(String.format("::: A Bundle with the 'id %s' does not exist :::", bundleId));
        }

        return bundleOp.get();
    }

    public BundleMod create(@NotNull BundleMod newBundle) {
        if (Objects.isNull(newBundle.getId())) {
            newBundle.setId(UUID.randomUUID());
        }
        checkForUniqueness(newBundle);
        return repository.save(newBundle);
    }

    public void update(UUID bundleId, String updateString, MediaType mediaType)
            throws ResourceNotFoundException, ParsingException {

        getOne(bundleId); // to check if a bundle with the given id exist
        Bundle updateParsed = fhirParserService.parseBundle(updateString, mediaType);
        updateParsed.setId("");
        BundleMod update = Objects.requireNonNull(Bundle2BundleMod.apply(updateParsed));

        update.setId(bundleId);
        repository.save(update);
    }

    public void delete(UUID bundleId) {
        getOne(bundleId);
        repository.deleteById(bundleId);
    }

    public List<BundleMod> search(BundleCriteria criteria) {
//        return repository.findAll(new BundleSpecs(criteria));

        return repository.findAll(); // todo
    }

    private void checkForUniqueness(@NotNull BundleMod bundle) {
        if (repository.existsById(bundle.getId())) {
            throw new ResourceBadRequestException(String.format("::: A Bundle with the id=%s already exist :::", bundle.getId()));
        }
    }

}
