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

import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

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
                    String.format("::: A Identifier with 'id = %s' does not exist :::", identifierId)
            );
        }

        return identifierOp.get();
    }

    public Identifier create(@NotNull Identifier newIdentifier) {
        checkForUniqueness(newIdentifier, repository);
        persistIdentifierComponents(newIdentifier, RequestOperation.Create);
        newIdentifier.setId(null);
        return repository.save(newIdentifier);
    }

    public Identifier update(UUID identifierId, @NotNull Identifier update) throws ResourceNotFoundException {
        getOne(identifierId); // to check if the update exist
        persistIdentifierComponents(update, RequestOperation.Update);
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
