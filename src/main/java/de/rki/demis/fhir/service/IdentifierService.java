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
        // Type
        if (Objects.nonNull(newIdentifier.getType())) {
            newIdentifier.setType(codeableConceptService.create(newIdentifier.getType()));
        }

        // System
        if (Objects.nonNull(newIdentifier.getSystem())) {
            newIdentifier.setSystem(uriTypeService.create(newIdentifier.getSystem()));
        }

        // Assigner
//        if (Objects.nonNull(newIdentifier.getAssigner())) {
//            newIdentifier.setAssigner(referenceService.create(newIdentifier.getAssigner())); // todo: committed because of circle
//        }


        newIdentifier.setId(null);
        return repository.save(newIdentifier);
    }

    public void update(UUID identifierId, @NotNull Identifier update) throws ResourceNotFoundException {
        Identifier identifierFound = getOne(identifierId);

        if (!Objects.equals(identifierFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

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

}
