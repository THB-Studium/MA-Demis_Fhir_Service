package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.CodeType;
import de.rki.demis.fhir.repository.CodeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

<<<<<<< HEAD
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CodeTypeService implements BaseService<CodeType> {
    private final CodeTypeRepository repository;


    public List<CodeType> listAll() {
        return repository.findAll();
    }

    public CodeType getOne(UUID codeTypeId) {
        Optional<CodeType> codeTypeOp = repository.findById(codeTypeId);

        if (codeTypeOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, CodeType.class.getSimpleName(), codeTypeId)
            );
        }

        return codeTypeOp.get();
    }

    public CodeType create(@NotNull CodeType newCodeType) {
        checkForUniqueness(newCodeType, repository);
        newCodeType.setId(null);
        return repository.save(newCodeType);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public CodeType update(UUID codeTypeId, @NotNull CodeType update) throws ResourceNotFoundException {
        getOne(codeTypeId); // to check if the update exist
=======
    public void update(UUID codeTypeId, @NotNull CodeType update) throws ResourceNotFoundException {
        getOne(codeTypeId);

        if (!codeTypeId.equals(update.getId())) {
            checkForUniqueness(update);
        }

>>>>>>> c598496 (update issues in BinaryService fixed)
=======
    public CodeType update(UUID codeTypeId, @NotNull CodeType update) throws ResourceNotFoundException {
        getOne(codeTypeId); // to check if the update exist
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        update.setId(codeTypeId);
        return repository.save(update);
    }

    public void delete(UUID codeTypeId) {
        getOne(codeTypeId);
        repository.deleteById(codeTypeId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

}
