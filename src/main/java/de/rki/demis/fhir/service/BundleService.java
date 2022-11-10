package de.rki.demis.fhir.service;

import de.rki.demis.fhir.exception.ParsingException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.exception.ResourceNotFoundException;
import de.rki.demis.fhir.model.BundleEntryComponent;
import de.rki.demis.fhir.model.BundleLinkComponent;
import de.rki.demis.fhir.model.BundleMod;
import de.rki.demis.fhir.repository.BundleEntryComponentRepository;
import de.rki.demis.fhir.repository.BundleLinkComponentRepository;
import de.rki.demis.fhir.repository.BundleRepository;
import de.rki.demis.fhir.repository.EnumerationRepository;
import de.rki.demis.fhir.search.criteria.BundleCriteria;
import de.rki.demis.fhir.search.specs.BundleSpecs;
import de.rki.demis.fhir.transfert.bundle.Bundle2BundleMod;
import de.rki.demis.fhir.util.service.FhirParserService;
import lombok.RequiredArgsConstructor;
import org.hl7.fhir.r4.model.Bundle;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleService {
    private final BundleRepository repository;
    private final MetaService metaService;
    private final UriTypeService uriTypeService;
    private final CodeTypeService codeTypeService;
    private final IdentifierService identifierService;
    private final EnumerationRepository enumerationRepository;
    private final BundleLinkComponentRepository bundleLinkComponentRepository;
    private final BundleLinkComponentService bundleLinkComponentService;
    private final BundleEntryComponentRepository bundleEntryComponentRepository;
    private final BundleEntryComponentService bundleEntryComponentService;
    private final SignatureService signatureService;
    private final FhirParserService fhirParserService;

    public List<BundleMod> listAll() {
        return repository.findAll();
    }

    public BundleMod getOne(UUID bundleId) {
        Optional<BundleMod> bundleOp = repository.findById(bundleId);

        if (bundleOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A Bundle with the 'id %s' does not exist :::", bundleId)
            );
        }

        return bundleOp.get();
    }

    public BundleMod create(@NotNull BundleMod newBundle) {
        // Meta
        if (Objects.nonNull(newBundle.getMeta())) {
            newBundle.setMeta(metaService.create(newBundle.getMeta()));
        }

        // ImplicitRules
        if (Objects.nonNull(newBundle.getImplicitRules())) {
            newBundle.setImplicitRules(uriTypeService
                    .create(newBundle.getImplicitRules()));
        }

        // Language
        if (Objects.nonNull(newBundle.getLanguage())) {
            newBundle.setLanguage(codeTypeService
                    .create(newBundle.getLanguage()));
        }

        // Identifier
        if (Objects.nonNull(newBundle.getIdentifier())) {
            newBundle.setIdentifier(identifierService
                    .create(newBundle.getIdentifier()));
        }

        // Type
        if (Objects.nonNull(newBundle.getType())) {
            if (Objects.isNull(newBundle.getType().getId()) ||
                    !enumerationRepository.existsById(newBundle.getType().getId())) {
                newBundle.setType(enumerationRepository.save(newBundle.getType()));
            }
        }

        // Link
        Set<BundleLinkComponent> link = new HashSet<>();
        newBundle.getLink().forEach(item -> {
            if (Objects.isNull(item.getId()) || !bundleLinkComponentRepository.existsById(item.getId())) {
                item = bundleLinkComponentService.create(item);
            }
            link.add(item);
        });

        // Entry
        Set<BundleEntryComponent> entry = new HashSet<>();
        newBundle.getEntry().forEach(item -> {
            if (Objects.isNull(item.getId()) || !bundleEntryComponentRepository.existsById(item.getId())) {
                item = bundleEntryComponentService.create(item);
            }
            entry.add(item);
        });

        // Signature
        if (Objects.nonNull(newBundle.getSignature())) {
            newBundle.setSignature(signatureService.create(newBundle.getSignature()));
        }

        newBundle.setLink(link);
        newBundle.setEntry(entry);
        newBundle.setId(null);
        return repository.save(newBundle);
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

    private void checkForUniqueness(@NotNull BundleMod bundle) {
        if (repository.existsById(bundle.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Bundle with the id=%s already exist :::", bundle.getId())
            );
        }
    }

}
