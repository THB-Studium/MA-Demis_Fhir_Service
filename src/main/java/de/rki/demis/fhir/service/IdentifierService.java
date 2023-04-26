package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Identifier;
import de.rki.demis.fhir.repository.IdentifierRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static de.rki.demis.fhir.util.constant.Constants.CREATE_OP;
import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;
import static de.rki.demis.fhir.util.service.PersistenceService.persistCodeableConceptEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class IdentifierService {
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
                    String.format("::: A Identifier with 'id = %s' does not exist :::", identifierId)
            );
        }

        return identifierOp.get();
    }

    public Identifier create(@NotNull Identifier newIdentifier) {
        persistIdentifierComponents(newIdentifier, CREATE_OP);
        newIdentifier.setId(null);
        return repository.save(newIdentifier);
    }

    public void update(UUID identifierId, @NotNull Identifier update) throws ResourceNotFoundException {
        getOne(identifierId);

        if (!Objects.equals(identifierId, update.getId())) {
            checkForUniqueness(update);
        }

        persistIdentifierComponents(update, UPDATE_OP);
        update.setId(identifierId);
        repository.save(update);
    }

    public void delete(UUID identifierId) {
        getOne(identifierId);
        repository.deleteById(identifierId);
    }

    private void checkForUniqueness(@NotNull Identifier identifier) {
        if (repository.existsById(identifier.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Identifier with the id=%s already exist :::", identifier.getId())
            );
        }
    }

    private void persistIdentifierComponents(@NotNull Identifier identifier, String requestOperation) {
        // Type
        if (Objects.nonNull(identifier.getType())) {
            identifier.setType(persistCodeableConceptEntity(identifier.getType(), codeableConceptService, requestOperation));
        }

        // System
        if (Objects.nonNull(identifier.getSystem())) {
            identifier.setSystem(persistUriTypeEntity(identifier.getSystem(), uriTypeService, requestOperation));
        }

//        // Assigner
//        if (Objects.nonNull(identifier.getAssigner())) {
//            identifier.setAssigner(persistReferenceEntity(identifier.getAssigner(), referenceService, requestOperation)); // todo: committed because of circle
//        }
    }

}
