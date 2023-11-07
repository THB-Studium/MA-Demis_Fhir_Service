package de.rki.demis.fhir.service;

import de.rki.demis.fhir.exception.ParsingException;
import de.rki.demis.fhir.exception.ResourceNotFoundException;
import de.rki.demis.fhir.model.BundleEntryComponent;
import de.rki.demis.fhir.model.BundleLinkComponent;
import de.rki.demis.fhir.model.BundleMod;
import de.rki.demis.fhir.repository.BundleRepository;
import de.rki.demis.fhir.search.criteria.BundleCriteria;
import de.rki.demis.fhir.search.specs.BundleSpecs;
import de.rki.demis.fhir.util.constant.RequestOperation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleService {
    private final BundleRepository repository;
    private final MetaService metaService;
    private final UriTypeService uriTypeService;
    private final CodeTypeService codeTypeService;
    private final IdentifierService identifierService;
    private final EnumerationBundleTypeService enumerationBundleTypeService;
    private final BundleLinkComponentService bundleLinkComponentService;
    private final BundleEntryComponentService bundleEntryComponentService;
    private final SignatureService signatureService;

    public List<BundleMod> listAll() {
        return repository.findAll();
    }

    public BundleMod getOne(UUID bundleId) {
        Optional<BundleMod> bundleOp = repository.findById(bundleId);

        if (bundleOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, BundleMod.class.getSimpleName(), bundleId)
            );
        }

        return bundleOp.get();
    }

    public BundleMod create(@NotNull BundleMod newBundle) {
        checkForUniqueness(newBundle, repository);
        persistBundleModComponents(newBundle, RequestOperation.Create);
        newBundle.setId(null);
        return repository.save(newBundle);
    }

    public void update(UUID bundleId, @NotNull BundleMod update)
            throws ResourceNotFoundException, ParsingException {
        getOne(bundleId);
        persistBundleModComponents(update, RequestOperation.Update);
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

    private void persistBundleModComponents(@NotNull BundleMod bundleMod, RequestOperation requestOperation) {
        Set<BundleLinkComponent> links = new HashSet<>();
        Set<BundleEntryComponent> entries = new HashSet<>();

        // Meta
        bundleMod.setMeta(persistEntity(bundleMod.getMeta(), metaService, requestOperation));

        // ImplicitRules
        bundleMod.setImplicitRules(persistEntity(bundleMod.getImplicitRules(), uriTypeService, requestOperation));

        // Language
        bundleMod.setLanguage(persistEntity(bundleMod.getLanguage(), codeTypeService, requestOperation));

        // Identifier
        bundleMod.setIdentifier(persistEntity(bundleMod.getIdentifier(), identifierService, requestOperation));

        // Type
        bundleMod.setType(persistEntity(bundleMod.getType(), enumerationBundleTypeService, requestOperation));

        // Link
        if (Objects.nonNull(bundleMod.getLink())) {
            bundleMod.getLink().forEach(item -> links.add(persistEntity(item, bundleLinkComponentService, requestOperation)));
        }

        // Entry
        if (Objects.nonNull(bundleMod.getEntry())) {
            bundleMod.getEntry().forEach(item -> entries.add(persistEntity(item, bundleEntryComponentService, requestOperation)));
        }

        // Signature
        bundleMod.setSignature(persistEntity(bundleMod.getSignature(), signatureService, requestOperation));

        bundleMod.setLink(links);
        bundleMod.setEntry(entries);
    }

}
