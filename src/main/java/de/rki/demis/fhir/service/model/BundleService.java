package de.rki.demis.fhir.service.model;

import de.rki.demis.fhir.exception.ParsingException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.exception.ResourceNotFoundException;
import de.rki.demis.fhir.model.BundleMod;
import de.rki.demis.fhir.repository.BundleRepository;
import de.rki.demis.fhir.search.criteria.BundleCriteria;
import de.rki.demis.fhir.search.specs.BundleSpecs;
import de.rki.demis.fhir.service.utils.FhirParserService;
import de.rki.demis.fhir.transfert.bundle.Bundle2BundleMod;
import lombok.RequiredArgsConstructor;
import org.hl7.fhir.r4.model.Bundle;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleService {
    private final BundleRepository repository;
    private final FhirParserService fhirParserService;

    public List<BundleMod> listAll() {
        return repository.findAll();
    }

    public BundleMod getOne(UUID bundleId) {
        Optional<BundleMod> bundleOp = repository.findById(bundleId);

        if (bundleOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("A Bundle with the 'id %s' does not exist", bundleId)
            );
        }

        return bundleOp.get();
    }

    public BundleMod create(String newBundleString, MediaType mediaType) throws ParsingException {
        Bundle bundle = fhirParserService.parseBundle(newBundleString, mediaType);
        BundleMod newBundleMod = Objects.requireNonNull(Bundle2BundleMod.apply(bundle));
        newBundleMod.setId(null);
        return repository.save(newBundleMod);
    }

    public void update(UUID bundleId, String updateString, MediaType mediaType)
            throws ResourceNotFoundException, ParsingException {

        BundleMod bundleModFound = getOne(bundleId);
        Bundle bundle = fhirParserService.parseBundle(updateString, mediaType);
        BundleMod update = Objects.requireNonNull(Bundle2BundleMod.apply(bundle));

        if (!bundleModFound.getId().equals(update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(bundleId);
        repository.save(update);
    }

    public void delete(UUID bundleId) {
        getOne(bundleId);
        repository.deleteById(bundleId);
    }

    public List<BundleMod> search(BundleCriteria criteria) {
        return repository.findAll(new BundleSpecs(criteria));
    }

    private void checkForUniqueness(BundleMod bundle) {
        if (repository.existsById(bundle.getId())) {
            throw new ResourceBadRequestException(
                    String.format("A Bundle with the id=%s already exist", bundle.getId())
            );
        }
    }

}
