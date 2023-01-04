package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.CodeType;
import de.rki.demis.fhir.repository.CodeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CodeTypeService {
    private final CodeTypeRepository repository;


    public List<CodeType> listAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public CodeType getOne(UUID codeTypeId) {
        Optional<CodeType> codeTypeOp = repository.findById(codeTypeId);

        if (codeTypeOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A CodeType with 'id = %s' does not exist :::", codeTypeId)
            );
        }

        return codeTypeOp.get();
    }

    public CodeType create(@NotNull CodeType newCodeType) {
        newCodeType.setId(UUID.randomUUID());
        return repository.save(newCodeType);
    }

    public void update(UUID codeTypeId, @NotNull CodeType update) throws ResourceNotFoundException {
        CodeType codeTypeFound = getOne(codeTypeId);

        if (!Objects.equals(codeTypeFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(codeTypeId);
        repository.save(update);
    }

    public void delete(UUID codeTypeId) {
        getOne(codeTypeId);
        repository.deleteById(codeTypeId);
    }

    private void checkForUniqueness(@NotNull CodeType codeType) {
        if (repository.existsById(codeType.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A CodeType with the id=%s already exist :::", codeType.getId())
            );
        }
    }

}
