package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.UriType;
import de.rki.demis.fhir.repository.UriTypeRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class UriTypeService {
    private final UriTypeRepository repository;


    public List<UriType> listAll() {
        return repository.findAll();
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
        newUriType.setId(null);
        return repository.save(newUriType);
    }

    public void update(UUID uriTypeId, @NotNull UriType update) throws ResourceNotFoundException {
        getOne(uriTypeId);

        if (!uriTypeId.equals(update.getId())) {
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
