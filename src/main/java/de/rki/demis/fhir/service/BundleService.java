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

<<<<<<< HEAD
<<<<<<< HEAD
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
=======
import static de.rki.demis.fhir.util.service.PersistenceService.persistBundleEntryComponentEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistBundleLinkComponentEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistCodeTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEnumerationBundleTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistIdentifierEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistMetaEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistSignatureEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;
>>>>>>> acf3b2c (wip)
=======
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)

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
<<<<<<< HEAD
<<<<<<< HEAD
        checkForUniqueness(newBundle, repository);
=======
>>>>>>> acf3b2c (wip)
=======
        checkForUniqueness(newBundle, repository);
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        persistBundleModComponents(newBundle, RequestOperation.Create);
        newBundle.setId(null);
        return repository.save(newBundle);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void update(UUID bundleId, @NotNull BundleMod update)
            throws ResourceNotFoundException, ParsingException {
        getOne(bundleId);
<<<<<<< HEAD
=======
    public void update(UUID bundleId, BundleMod update)
=======
    public void update(UUID bundleId, @NotNull BundleMod update)
>>>>>>> f67cebc (some refactorings done)
            throws ResourceNotFoundException, ParsingException {
        getOne(bundleId);

        if (!bundleId.equals(update.getId())) {
            checkForUniqueness(update);
        }

>>>>>>> acf3b2c (wip)
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
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

<<<<<<< HEAD
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

=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
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
