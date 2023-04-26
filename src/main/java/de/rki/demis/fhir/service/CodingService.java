package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.CodingRepository;
import de.rki.demis.fhir.util.constant.RequestOperation;
=======
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.CodingRepository;
>>>>>>> c598496 (update issues in BinaryService fixed)
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

<<<<<<< HEAD
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
=======
import static de.rki.demis.fhir.util.constant.Constants.CREATE_OP;
import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;
import static de.rki.demis.fhir.util.service.PersistenceService.persistCodeTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistExtensionEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;
>>>>>>> c598496 (update issues in BinaryService fixed)

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CodingService implements BaseService<Coding> {
    private final CodingRepository repository;
    private final ExtensionService extensionService;
    private final UriTypeService uriTypeService;
    private final CodeTypeService codeTypeService;


    public List<Coding> listAll() {
        return repository.findAll();
    }

    public Coding getOne(UUID metaId) {
        Optional<Coding> metaOp = repository.findById(metaId);

        if (metaOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, Coding.class.getSimpleName(), metaId)
            );
        }

        return metaOp.get();
    }

    public Coding create(@NotNull Coding newCoding) {
<<<<<<< HEAD
        checkForUniqueness(newCoding, repository);
        persistCodingComponents(newCoding, RequestOperation.Create);
=======
        persistCodingComponents(newCoding, CREATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
        newCoding.setId(null);
        return repository.save(newCoding);
    }

<<<<<<< HEAD
    public Coding update(UUID metaId, @NotNull Coding update) throws ResourceNotFoundException {
        getOne(metaId); // to check if the update exist
        persistCodingComponents(update, RequestOperation.Update);
=======
    public void update(UUID metaId, @NotNull Coding update) throws ResourceNotFoundException {
        // to check if the update exist
        getOne(metaId);

        // to check the uniqueness of the update
        if (!Objects.equals(metaId, update.getId())) {
            checkForUniqueness(update);
        }

        persistCodingComponents(update, UPDATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
        update.setId(metaId);
        return repository.save(update);
    }

    public void delete(UUID metaId) {
        getOne(metaId);
        repository.deleteById(metaId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

    private void persistCodingComponents(@NotNull Coding coding, RequestOperation requestOperation) {
        Set<Extension> extension = new HashSet<>();

        // Extension
        if (Objects.nonNull(coding.getExtension())) {
            coding.getExtension().forEach(item -> extension.add(persistEntity(item, extensionService, requestOperation))
            );
        }

        // System
        if (Objects.nonNull(coding.getSystem())) {
            coding.setSystem(persistEntity(coding.getSystem(), uriTypeService, requestOperation));
        }

        // Code
        if (Objects.nonNull(coding.getCode())) {
            coding.setCode(persistEntity(coding.getCode(), codeTypeService, requestOperation));
        }

        coding.setExtension(extension);
    }

    private void persistCodingComponents(@NotNull Coding coding, String requestOperation) {
        // Extension
        Set<Extension> extension = new HashSet<>();
        coding.getExtension().forEach(item -> extension.add(persistExtensionEntity(item, extensionService, requestOperation)));

        // System
        if (Objects.nonNull(coding.getSystem())) {
            coding.setSystem(persistUriTypeEntity(coding.getSystem(), uriTypeService, requestOperation));
        }

        // Code
        if (Objects.nonNull(coding.getCode())) {
            coding.setCode(persistCodeTypeEntity(coding.getCode(), codeTypeService, requestOperation));
        }

        coding.setExtension(extension);
    }

}
