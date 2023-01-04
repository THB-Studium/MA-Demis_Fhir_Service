package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.UriType;
import de.rki.demis.fhir.repository.UriTypeRepository;
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
public class UriTypeService {
    private final UriTypeRepository repository;



    public List<UriType> listAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public UriType getOne(UUID uriTypeId) {
        Optional<UriType> uriTypeOp = repository.findById(uriTypeId);

        if (uriTypeOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A UriType with 'id = %s' does not exist :::", uriTypeId)
            );
        }

        return uriTypeOp.get();
    }

    public UriType create(@NotNull UriType newUriType) {
        newUriType.setId(UUID.randomUUID());
        return repository.save(newUriType);
    }

    public void update(UUID uriTypeId, @NotNull UriType update) throws ResourceNotFoundException {
        UriType uriTypeFound = getOne(uriTypeId);

        if (!Objects.equals(uriTypeFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(uriTypeId);
        repository.save(update);
    }

    public void delete(UUID uriTypeId) {
        getOne(uriTypeId);
        repository.deleteById(uriTypeId);
    }

    private void checkForUniqueness(@NotNull UriType uriType) {
        if (repository.existsById(uriType.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A UriType with the id=%s already exist :::", uriType.getId())
            );
        }
    }

}
