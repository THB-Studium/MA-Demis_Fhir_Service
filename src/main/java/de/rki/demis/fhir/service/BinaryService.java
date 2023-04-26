package de.rki.demis.fhir.service;

import de.rki.demis.fhir.exception.ParsingException;
import de.rki.demis.fhir.exception.ResourceNotFoundException;
import de.rki.demis.fhir.model.BinaryMod;
import de.rki.demis.fhir.repository.BinaryRepository;
import de.rki.demis.fhir.search.criteria.BinaryCriteria;
import de.rki.demis.fhir.search.specs.BinarySpecs;
<<<<<<< HEAD
import de.rki.demis.fhir.util.constant.RequestOperation;
=======
>>>>>>> c598496 (update issues in BinaryService fixed)
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

<<<<<<< HEAD
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
=======
import static de.rki.demis.fhir.util.constant.Constants.CREATE_OP;
import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;
import static de.rki.demis.fhir.util.service.PersistenceService.persistCodeTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistMetaEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistReferenceEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistResourceEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;
>>>>>>> c598496 (update issues in BinaryService fixed)

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BinaryService {
    private final BinaryRepository repository;
    private final MetaService metaService;
    private final UriTypeService uriTypeService;
    private final CodeTypeService codeTypeService;
    private final ReferenceService referenceService;
    private final ResourceService resourceService;


    public List<BinaryMod> listAll() {
        return repository.findAll();
    }

    public BinaryMod getOne(UUID binaryModId) {
        Optional<BinaryMod> binaryOp = repository.findById(binaryModId);

        if (binaryOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, BinaryMod.class.getSimpleName(), binaryModId)
            );
        }

        return binaryOp.get();
    }

    public BinaryMod create(@NotNull BinaryMod newBinaryMod) {
<<<<<<< HEAD
        checkForUniqueness(newBinaryMod, repository);
        persistBinaryModComponents(newBinaryMod, RequestOperation.Create);
=======
        persistBinaryModComponents(newBinaryMod, CREATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
        newBinaryMod.setId(null);
        return repository.save(newBinaryMod);
    }

    public void update(@NotNull UUID binaryId, @NotNull BinaryMod update)
            throws ResourceNotFoundException, ParsingException {
<<<<<<< HEAD
        getOne(binaryId); // to check if the update exist
        persistBinaryModComponents(update, RequestOperation.Update);
=======

        // to check if the update exist
        getOne(binaryId);

        // to check the uniqueness of the update
        if (!Objects.equals(binaryId, update.getId())) {
            checkForUniqueness(update);
        }

        persistBinaryModComponents(update, UPDATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
        update.setId(binaryId);
        repository.save(update);
    }

    public void delete(UUID binaryId) {
        getOne(binaryId);
        repository.deleteById(binaryId);
    }

    public List<BinaryMod> search(BinaryCriteria criteria) {
        return repository.findAll(new BinarySpecs(criteria));
    }

<<<<<<< HEAD
    private void persistBinaryModComponents(@NotNull BinaryMod binary, RequestOperation requestOperation) {

        // Meta
        binary.setMeta(persistEntity(binary.getMeta(), metaService, requestOperation));

        // to update ImplicitRules
        binary.setImplicitRules(persistEntity(binary.getImplicitRules(), uriTypeService, requestOperation));

        // to update Language
        binary.setLanguage(persistEntity(binary.getLanguage(), codeTypeService, requestOperation));

        // to update SecurityContext
        binary.setSecurityContext(persistEntity(binary.getSecurityContext(), referenceService, requestOperation));

        // to update SecurityContextTarget
        binary.setSecurityContextTarget(persistEntity(binary.getSecurityContextTarget(), resourceService, requestOperation));

=======
    private void checkForUniqueness(@NotNull BinaryMod binary) {
        if (Objects.nonNull(binary.getId()) && repository.existsById(binary.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BinaryMod with the id=%s already exist :::", binary.getId())
            );
        }
>>>>>>> c598496 (update issues in BinaryService fixed)
    }

    private void persistBinaryModComponents(@NotNull BinaryMod binary, String requestOperation) {

        // Meta
        binary.setMeta(persistMetaEntity(binary.getMeta(), metaService, requestOperation));

        // to update ImplicitRules
        binary.setImplicitRules(persistUriTypeEntity(binary.getImplicitRules(), uriTypeService, requestOperation));

        // to update Language
        binary.setLanguage(persistCodeTypeEntity(binary.getLanguage(), codeTypeService, requestOperation));

        // to update SecurityContext
        binary.setSecurityContext(persistReferenceEntity(binary.getSecurityContext(), referenceService, requestOperation));

        // to update SecurityContextTarget
        binary.setSecurityContextTarget(persistResourceEntity(binary.getSecurityContextTarget(), resourceService, requestOperation));

    }

}
