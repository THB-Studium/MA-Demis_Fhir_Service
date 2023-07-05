package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.CodingRepository;
import de.rki.demis.fhir.util.constant.RequestOperation;
=======
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.CodingRepository;
<<<<<<< HEAD
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
import de.rki.demis.fhir.util.constant.RequestOperation;
>>>>>>> acf3b2c (wip)
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
<<<<<<< HEAD
<<<<<<< HEAD
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
=======
import static de.rki.demis.fhir.util.constant.Constants.CREATE_OP;
import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;
=======
>>>>>>> acf3b2c (wip)
import static de.rki.demis.fhir.util.service.PersistenceService.persistCodeTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistExtensionEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)

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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        checkForUniqueness(newCoding, repository);
        persistCodingComponents(newCoding, RequestOperation.Create);
=======
        persistCodingComponents(newCoding, CREATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
        persistCodingComponents(newCoding, RequestOperation.Create);
>>>>>>> acf3b2c (wip)
        newCoding.setId(null);
        return repository.save(newCoding);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public Coding update(UUID metaId, @NotNull Coding update) throws ResourceNotFoundException {
        getOne(metaId); // to check if the update exist
        persistCodingComponents(update, RequestOperation.Update);
=======
    public void update(UUID metaId, @NotNull Coding update) throws ResourceNotFoundException {
        // to check if the update exist
        getOne(metaId);

        // to check the uniqueness of the update
        if (!metaId.equals(update.getId())) {
            checkForUniqueness(update);
        }

<<<<<<< HEAD
        persistCodingComponents(update, UPDATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
=======
    public Coding update(UUID metaId, @NotNull Coding update) throws ResourceNotFoundException {
        getOne(metaId); // to check if the update exist
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        persistCodingComponents(update, RequestOperation.Update);
>>>>>>> acf3b2c (wip)
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
<<<<<<< HEAD
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
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
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

}
