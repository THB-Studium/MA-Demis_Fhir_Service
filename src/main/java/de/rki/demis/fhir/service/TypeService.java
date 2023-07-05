package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.Type;
import de.rki.demis.fhir.repository.TypeRepository;
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
public class TypeService implements BaseService<Type> {
    private final TypeRepository repository;


    public List<Type> listAll() {
        return repository.findAll();
    }

    public Type getOne(UUID typeId) {
        Optional<Type> typeOp = repository.findById(typeId);

        if (typeOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, Type.class.getSimpleName(), typeId)
            );
        }

        return typeOp.get();
    }

    public Type create(@NotNull Type newType) {
        checkForUniqueness(newType, repository);
        newType.setId(null);
        return repository.save(newType);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public Type update(UUID typeId, @NotNull Type update) throws ResourceNotFoundException {
        getOne(typeId); // to check if the update exist
=======
    public void update(UUID typeId, @NotNull Type update) throws ResourceNotFoundException {
        getOne(typeId);

        if (!typeId.equals(update.getId())) {
            checkForUniqueness(update);
        }

>>>>>>> acf3b2c (wip)
=======
    public Type update(UUID typeId, @NotNull Type update) throws ResourceNotFoundException {
        getOne(typeId); // to check if the update exist
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        update.setId(typeId);
        return repository.save(update);
    }

    public void delete(UUID typeId) {
        getOne(typeId);
        repository.deleteById(typeId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

}
