package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.CodeableConcept;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.repository.CodeableConceptRepository;
<<<<<<< HEAD
<<<<<<< HEAD
import de.rki.demis.fhir.util.constant.RequestOperation;
=======
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
import de.rki.demis.fhir.util.constant.RequestOperation;
>>>>>>> acf3b2c (wip)
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 694cae4 (some refactorings are done)
import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
=======
import static de.rki.demis.fhir.util.constant.Constants.CREATE_OP;
import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;
=======
>>>>>>> acf3b2c (wip)
import static de.rki.demis.fhir.util.service.PersistenceService.persistCodingEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistExtensionEntity;
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CodeableConceptService implements BaseService<CodeableConcept> {
    private final CodeableConceptRepository repository;
    private final CodingService codingService;
    private final ExtensionService extensionService;


    public List<CodeableConcept> listAll() {
        return repository.findAll();
    }

    public CodeableConcept getOne(UUID codeableConceptId) {
        Optional<CodeableConcept> codeableConceptOp = repository.findById(codeableConceptId);

        if (codeableConceptOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(NOT_EXIST_MSG, CodeableConcept.class.getSimpleName(), codeableConceptId)
            );
        }

        return codeableConceptOp.get();
    }

    public CodeableConcept create(@NotNull CodeableConcept newCodeableConcept) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        checkForUniqueness(newCodeableConcept, repository);
        persistCodeableConceptComponents(newCodeableConcept, RequestOperation.Create);
=======
        persistCodeableConceptComponents(newCodeableConcept, CREATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
        persistCodeableConceptComponents(newCodeableConcept, RequestOperation.Create);
>>>>>>> acf3b2c (wip)
        newCodeableConcept.setId(null);
        return repository.save(newCodeableConcept);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public CodeableConcept update(UUID codeableConceptId, @NotNull CodeableConcept update) throws ResourceNotFoundException {
        getOne(codeableConceptId); // to check if the update exist
        persistCodeableConceptComponents(update, RequestOperation.Update);
=======
    public void update(UUID codeableConceptId, @NotNull CodeableConcept update) throws ResourceNotFoundException {
        getOne(codeableConceptId);

        if (!codeableConceptId.equals(update.getId())) {
            checkForUniqueness(update);
        }

<<<<<<< HEAD
        persistCodeableConceptComponents(update, UPDATE_OP);
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
=======
    public CodeableConcept update(UUID codeableConceptId, @NotNull CodeableConcept update) throws ResourceNotFoundException {
        getOne(codeableConceptId); // to check if the update exist
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
        persistCodeableConceptComponents(update, RequestOperation.Update);
>>>>>>> acf3b2c (wip)
        update.setId(codeableConceptId);
        return repository.save(update);
    }

    public void delete(UUID codeableConceptId) {
        getOne(codeableConceptId);
        repository.deleteById(codeableConceptId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
<<<<<<< HEAD
    }

    private void persistCodeableConceptComponents(@NotNull CodeableConcept codeableConcept, RequestOperation requestOperation) {
        Set<Coding> coding = new HashSet<>();
        Set<Extension> extension = new HashSet<>();

        // Coding
        if (Objects.nonNull(codeableConcept.getCoding())) {
            codeableConcept.getCoding().forEach(item ->
                    coding.add(persistEntity(item, codingService, requestOperation))
            );
        }

        // Extension
        if (Objects.nonNull(codeableConcept.getExtension())) {
            codeableConcept.getExtension().forEach(item ->
                    extension.add(persistEntity(item, extensionService, requestOperation))
            );
        }

        codeableConcept.setCoding(coding);
        codeableConcept.setExtension(extension);
=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
    }

    private void persistCodeableConceptComponents(@NotNull CodeableConcept codeableConcept, RequestOperation requestOperation) {
        Set<Coding> coding = new HashSet<>();
        Set<Extension> extension = new HashSet<>();

        // Coding
        if (Objects.nonNull(codeableConcept.getCoding())) {
            codeableConcept.getCoding().forEach(item ->
                    coding.add(persistEntity(item, codingService, requestOperation))
            );
        }

        // Extension
        if (Objects.nonNull(codeableConcept.getExtension())) {
            codeableConcept.getExtension().forEach(item ->
                    extension.add(persistEntity(item, extensionService, requestOperation))
            );
        }

        codeableConcept.setCoding(coding);
        codeableConcept.setExtension(extension);
    }

}
