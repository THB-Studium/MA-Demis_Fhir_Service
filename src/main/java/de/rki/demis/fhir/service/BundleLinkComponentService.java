package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.BundleLinkComponent;
import de.rki.demis.fhir.repository.BundleLinkComponentRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

<<<<<<< HEAD
<<<<<<< HEAD
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
=======
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
>>>>>>> 694cae4 (some refactorings are done)
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BundleLinkComponentService implements BaseService<BundleLinkComponent> {
    private final BundleLinkComponentRepository repository;


    public List<BundleLinkComponent> listAll() {
        return repository.findAll();
    }

    public BundleLinkComponent getOne(UUID bundleLinkComponentId) {
        Optional<BundleLinkComponent> bundleLinkComponentOp = repository.findById(bundleLinkComponentId);

        if (bundleLinkComponentOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, BundleLinkComponent.class.getSimpleName(), bundleLinkComponentId)
            );
        }

        return bundleLinkComponentOp.get();
    }

    public BundleLinkComponent create(@NotNull BundleLinkComponent newBundleLinkComponent) {
        checkForUniqueness(newBundleLinkComponent, repository);
        newBundleLinkComponent.setId(null);
        return repository.save(newBundleLinkComponent);
    }

    public BundleLinkComponent update(UUID bundleLinkComponentId, @NotNull BundleLinkComponent update)
            throws ResourceNotFoundException {
<<<<<<< HEAD
<<<<<<< HEAD
        getOne(bundleLinkComponentId); // to check if the update exist
=======
        getOne(bundleLinkComponentId);

        if (!bundleLinkComponentId.equals(update.getId())) {
            checkForUniqueness(update);
        }

>>>>>>> acf3b2c (wip)
=======
        getOne(bundleLinkComponentId); // to check if the update exist
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        update.setId(bundleLinkComponentId);
        return repository.save(update);
    }

    public void delete(UUID bundleLinkComponentId) {
        getOne(bundleLinkComponentId);
        repository.deleteById(bundleLinkComponentId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

}
