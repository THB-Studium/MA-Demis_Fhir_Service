package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.CodeType;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.CodeTypeRepository;
import de.rki.demis.fhir.repository.CodingRepository;
import de.rki.demis.fhir.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CodingService {
    private final CodingRepository repository;
    private final ExtensionRepository extensionRepository;
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
                    String.format("::: A Coding with 'id = %s' does not exist :::", metaId)
            );
        }

        return metaOp.get();
    }

    public Coding create(@NotNull Coding newCoding) {
//        Set<Extension> extension = new HashSet<>();
//        newCoding.getExtension().forEach(item -> {
//            if (Objects.isNull(item.getId()) || !extensionRepository.existsById(item.getId())) {
//                item = extensionService.create(item);
//            }
//            extension.add(item);
//        });

        // System
        if (Objects.nonNull(newCoding.getSystem())) {
            newCoding.setSystem(uriTypeService.create(newCoding.getSystem()));
        }

        // Code
        if (Objects.nonNull(newCoding.getCode())) {
            newCoding.setCode(codeTypeService.create(newCoding.getCode()));
        }

        return repository.save(newCoding);
    }

    public void update(UUID metaId, @NotNull Coding update)
            throws ResourceNotFoundException {

        Coding metaFound = getOne(metaId);

        if (!Objects.equals(metaFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(metaId);
        repository.save(update);
    }

    public void delete(UUID metaId) {
        getOne(metaId);
        repository.deleteById(metaId);
    }

    private void checkForUniqueness(@NotNull Coding meta) {
        if (repository.existsById(meta.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Coding with the id=%s already exist :::", meta.getId())
            );
        }
    }

}
