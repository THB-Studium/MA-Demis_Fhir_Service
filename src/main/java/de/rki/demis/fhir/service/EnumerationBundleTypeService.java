package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Enumeration;
import de.rki.demis.fhir.repository.EnumerationBundleTypeRepository;
import de.rki.demis.fhir.util.fhir_object.enums.BundleType;
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
public class EnumerationBundleTypeService {
    private final EnumerationBundleTypeRepository repository;


    public List<Enumeration<BundleType>> listAll() {
        return repository.findAll();
    }

    public Enumeration<BundleType> getOne(UUID enumerationId) {
        Optional<Enumeration<BundleType>> enumerationOp = repository.findById(enumerationId);

        if (enumerationOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A Enumeration Bundle Type with 'id = %s' does not exist :::", enumerationId)
            );
        }

        return enumerationOp.get();
    }

    public Enumeration<BundleType> create(@NotNull Enumeration<BundleType> newEnumeration) {
        newEnumeration.setId(null);
        return repository.save(newEnumeration);
    }

    public void update(UUID enumerationId, @NotNull Enumeration<BundleType> update) throws ResourceNotFoundException {
        getOne(enumerationId);

        if (!enumerationId.equals(update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(enumerationId);
        repository.save(update);
    }

    public void delete(UUID enumerationId) {
        getOne(enumerationId);
        repository.deleteById(enumerationId);
    }

    private void checkForUniqueness(@NotNull Enumeration<BundleType> enumeration) {
        if (repository.existsById(enumeration.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Enumeration Bundle Type with the id=%s already exist :::", enumeration.getId())
            );
        }
    }

}
