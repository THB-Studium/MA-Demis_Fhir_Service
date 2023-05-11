package de.rki.demis.fhir.service;

import de.rki.demis.fhir.exception.ParsingException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.exception.ResourceNotFoundException;
import de.rki.demis.fhir.model.BinaryMod;
import de.rki.demis.fhir.repository.BinaryRepository;
import de.rki.demis.fhir.search.criteria.BinaryCriteria;
import de.rki.demis.fhir.search.specs.BinarySpecs;
import de.rki.demis.fhir.util.constant.RequestOperation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static de.rki.demis.fhir.util.service.PersistenceService.persistCodeTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistMetaEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistReferenceEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistResourceEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;

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
                    String.format("::: A Binary with 'id = %s' does not exist :::", binaryModId)
            );
        }

        return binaryOp.get();
    }

    public BinaryMod create(@NotNull BinaryMod newBinaryMod) {
        persistBinaryModComponents(newBinaryMod, RequestOperation.Create);
        newBinaryMod.setId(null);
        return repository.save(newBinaryMod);
    }

    public void update(@NotNull UUID binaryId, @NotNull BinaryMod update)
            throws ResourceNotFoundException, ParsingException {

        // to check if the update exist
        getOne(binaryId);

        // to check the uniqueness of the update
        if (!Objects.equals(binaryId, update.getId())) {
            checkForUniqueness(update);
        }

        persistBinaryModComponents(update, RequestOperation.Update);
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

    private void checkForUniqueness(@NotNull BinaryMod binary) {
        if (Objects.nonNull(binary.getId()) && repository.existsById(binary.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BinaryMod with the id=%s already exist :::", binary.getId())
            );
        }
    }

    private void persistBinaryModComponents(@NotNull BinaryMod binary, RequestOperation requestOperation) {

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
