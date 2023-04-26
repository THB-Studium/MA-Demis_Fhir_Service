package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.Enumeration;
import de.rki.demis.fhir.repository.EnumerationBundleTypeRepository;
import de.rki.demis.fhir.util.fhir_object.enums.BundleType;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class EnumerationBundleTypeService implements BaseService<Enumeration<BundleType>> {
    private final EnumerationBundleTypeRepository repository;


    public List<Enumeration<BundleType>> listAll() {
        return repository.findAll();
    }

    public Enumeration<BundleType> getOne(UUID enumerationId) {
        Optional<Enumeration<BundleType>> enumerationOp = repository.findById(enumerationId);

        if (enumerationOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, "Enumeration-" + BundleType.class.getSimpleName(), enumerationId)
            );
        }

        return enumerationOp.get();
    }

    public Enumeration<BundleType> create(@NotNull Enumeration<BundleType> newEnumeration) {
        checkForUniqueness(newEnumeration, repository);
        newEnumeration.setId(null);
        return repository.save(newEnumeration);
    }

    public Enumeration<BundleType> update(UUID enumerationId, @NotNull Enumeration<BundleType> update) throws ResourceNotFoundException {
        getOne(enumerationId);// to check if the update exist
        update.setId(enumerationId);
        return repository.save(update);
    }

    public void delete(UUID enumerationId) {
        getOne(enumerationId);
        repository.deleteById(enumerationId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

}
