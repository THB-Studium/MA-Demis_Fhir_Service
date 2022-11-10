package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.model.Reference;
import de.rki.demis.fhir.repository.ExtensionRepository;
import de.rki.demis.fhir.repository.ReferenceRepository;
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

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ReferenceService {
    private final ReferenceRepository repository;
    private final ExtensionRepository extensionRepository;
    private final ExtensionService extensionService;
    private final UriTypeService uriTypeService;
    private final IdentifierService identifierService;


    public List<Reference> listAll() {
        return repository.findAll();
    }

    public Reference getOne(UUID referenceId) {
        Optional<Reference> referenceOp = repository.findById(referenceId);

        if (referenceOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A Reference with 'id = %s' does not exist :::", referenceId)
            );
        }

        return referenceOp.get();
    }

    public Reference create(@NotNull Reference newReference) {
        // extension
        Set<Extension> extension = new HashSet<>();
        newReference.getExtension().forEach(item -> {
            if (Objects.isNull(item.getId()) || !extensionRepository.existsById(item.getId())) {
                item = extensionService.create(item);
            }
            extension.add(item);
        });

        // Type
        if (Objects.nonNull(newReference.getType())) {
            newReference.setType(uriTypeService.create(newReference.getType()));
        }

        // Identifier
        if (Objects.nonNull(newReference.getIdentifier())) {
            newReference.setIdentifier(identifierService.create(newReference.getIdentifier()));
        }

        newReference.setExtension(extension);
        newReference.setId(null);
        return repository.save(newReference);
    }

    public void update(UUID referenceId, @NotNull Reference update) throws ResourceNotFoundException {
        Reference referenceFound = getOne(referenceId);

        if (!Objects.equals(referenceFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(referenceId);
        repository.save(update);
    }

    public void delete(UUID referenceId) {
        getOne(referenceId);
        repository.deleteById(referenceId);
    }

    private void checkForUniqueness(@NotNull Reference reference) {
        if (repository.existsById(reference.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Reference with the id=%s already exist :::", reference.getId())
            );
        }
    }

}
