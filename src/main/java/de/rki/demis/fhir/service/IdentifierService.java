package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.Identifier;
import de.rki.demis.fhir.repository.IdentifierRepository;
import de.rki.demis.fhir.util.constant.RequestOperation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 694cae4 (some refactorings are done)
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
=======
import static de.rki.demis.fhir.util.constant.Constants.CREATE_OP;
import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;
=======
>>>>>>> acf3b2c (wip)
import static de.rki.demis.fhir.util.service.PersistenceService.persistCodeableConceptEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class IdentifierService implements BaseService<Identifier> {
    private final IdentifierRepository repository;
    private final UriTypeService uriTypeService;
    private final CodeableConceptService codeableConceptService;


    public List<Identifier> listAll() {
        return repository.findAll();
    }

    public Identifier getOne(UUID identifierId) {
        Optional<Identifier> identifierOp = repository.findById(identifierId);

        if (identifierOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, Identifier.class.getSimpleName(), identifierId)
            );
        }

        return identifierOp.get();
    }

    public Identifier create(@NotNull Identifier newIdentifier) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        checkForUniqueness(newIdentifier, repository);
        persistIdentifierComponents(newIdentifier, RequestOperation.Create);
=======
        persistIdentifierComponents(newIdentifier, CREATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
        persistIdentifierComponents(newIdentifier, RequestOperation.Create);
>>>>>>> acf3b2c (wip)
        newIdentifier.setId(null);
        return repository.save(newIdentifier);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public Identifier update(UUID identifierId, @NotNull Identifier update) throws ResourceNotFoundException {
        getOne(identifierId); // to check if the update exist
        persistIdentifierComponents(update, RequestOperation.Update);
=======
    public void update(UUID identifierId, @NotNull Identifier update) throws ResourceNotFoundException {
        getOne(identifierId);

        if (!identifierId.equals(update.getId())) {
            checkForUniqueness(update);
        }

<<<<<<< HEAD
        persistIdentifierComponents(update, UPDATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
=======
    public Identifier update(UUID identifierId, @NotNull Identifier update) throws ResourceNotFoundException {
        getOne(identifierId); // to check if the update exist
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        persistIdentifierComponents(update, RequestOperation.Update);
>>>>>>> acf3b2c (wip)
        update.setId(identifierId);
        return repository.save(update);
    }

    public void delete(UUID identifierId) {
        getOne(identifierId);
        repository.deleteById(identifierId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
<<<<<<< HEAD
    }

    private void persistIdentifierComponents(@NotNull Identifier identifier, RequestOperation requestOperation) {
        // Type
        if (Objects.nonNull(identifier.getType())) {
            identifier.setType(persistEntity(identifier.getType(), codeableConceptService, requestOperation));
        }

        // System
        if (Objects.nonNull(identifier.getSystem())) {
            identifier.setSystem(persistEntity(identifier.getSystem(), uriTypeService, requestOperation));
        }

//        // Assigner
//        if (Objects.nonNull(identifier.getAssigner())) {
//            identifier.setAssigner(persistEntity(identifier.getAssigner(), referenceService, requestOperation)); // todo: committed because of circle
//        }
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    }

    private void persistIdentifierComponents(@NotNull Identifier identifier, RequestOperation requestOperation) {
        // Type
        if (Objects.nonNull(identifier.getType())) {
            identifier.setType(persistEntity(identifier.getType(), codeableConceptService, requestOperation));
        }

        // System
        if (Objects.nonNull(identifier.getSystem())) {
            identifier.setSystem(persistEntity(identifier.getSystem(), uriTypeService, requestOperation));
        }

//        // Assigner
//        if (Objects.nonNull(identifier.getAssigner())) {
//            identifier.setAssigner(persistEntity(identifier.getAssigner(), referenceService, requestOperation)); // todo: committed because of circle
//        }
    }

}
