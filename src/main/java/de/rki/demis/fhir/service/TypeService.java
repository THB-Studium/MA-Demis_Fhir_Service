package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Type;
import de.rki.demis.fhir.repository.TypeRepository;
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
public class TypeService {
    private final TypeRepository repository;



    public List<Type> listAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Type getOne(UUID typeId) {
        Optional<Type> typeOp = repository.findById(typeId);

        if (typeOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A Type with 'id = %s' does not exist :::", typeId)
            );
        }

        return typeOp.get();
    }

    public Type create(@NotNull Type newType) {
        newType.setId(UUID.randomUUID());
        return repository.save(newType);
    }

    public void update(UUID typeId, @NotNull Type update) throws ResourceNotFoundException {
        Type typeFound = getOne(typeId);

        if (!Objects.equals(typeFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(typeId);
        repository.save(update);
    }

    public void delete(UUID typeId) {
        getOne(typeId);
        repository.deleteById(typeId);
    }

    private void checkForUniqueness(@NotNull Type type) {
        if (repository.existsById(type.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Type with the id=%s already exist :::", type.getId())
            );
        }
    }

}
