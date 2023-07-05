package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.UriType;
import de.rki.demis.fhir.repository.UriTypeRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class UriTypeService implements BaseService<UriType> {
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
        checkForUniqueness(newUriType, repository);
        newUriType.setId(null);
        return repository.save(newUriType);
    }

    public UriType update(UUID uriTypeId, @NotNull UriType update) throws ResourceNotFoundException {
        getOne(uriTypeId); // to check if the update exist
        update.setId(uriTypeId);
        return repository.save(update);
    }

    public void delete(UUID uriTypeId) {
        getOne(uriTypeId);
        repository.deleteById(uriTypeId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

}
