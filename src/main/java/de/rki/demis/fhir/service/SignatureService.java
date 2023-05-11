package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.model.Signature;
import de.rki.demis.fhir.repository.SignatureRepository;
import de.rki.demis.fhir.util.constant.RequestOperation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static de.rki.demis.fhir.util.service.PersistenceService.persistCodeTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistCodingEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistExtensionEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistReferenceEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistResourceEntity;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class SignatureService {
    private final SignatureRepository repository;
    private final ExtensionService extensionService;
    private final CodingService codingService;
    private final ReferenceService referenceService;
    private final ResourceService resourceService;
    private final CodeTypeService codeTypeService;


    public List<Signature> listAll() {
        return repository.findAll();
    }

    public Signature getOne(UUID signatureId) {
        Optional<Signature> signatureOp = repository.findById(signatureId);

        if (signatureOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A Signature with 'id = %s' does not exist :::", signatureId)
            );
        }

        return signatureOp.get();
    }

    public Signature create(@NotNull Signature newSignature) {
        persistSignatureComponents(newSignature, RequestOperation.Create);
        newSignature.setId(null);
        return repository.save(newSignature);
    }

    public void update(UUID signatureId, @NotNull Signature update) throws ResourceNotFoundException {
        getOne(signatureId);

        if (!signatureId.equals(update.getId())) {
            checkForUniqueness(update);
        }

        persistSignatureComponents(update, RequestOperation.Update);
        update.setId(signatureId);
        repository.save(update);
    }

    public void delete(UUID signatureId) {
        getOne(signatureId);
        repository.deleteById(signatureId);
    }

    private void checkForUniqueness(@NotNull Signature signature) {
        if (repository.existsById(signature.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Signature with the id=%s already exist :::", signature.getId())
            );
        }
    }

    private void persistSignatureComponents(@NotNull Signature signature, RequestOperation requestOperation) {
        Set<Extension> extensions = new HashSet<>();
        Set<Coding> types = new HashSet<>();

        // Extension
        if (Objects.nonNull(signature.getExtension())) {
            signature.getExtension().forEach(item ->
                    extensions.add(persistExtensionEntity(item, extensionService, requestOperation)));
        }

        // Type
        if (Objects.nonNull(signature.getType())){
            signature.getType().forEach(item ->
                    types.add(persistCodingEntity(item, codingService, requestOperation)));
        }

        // Who
        signature.setWho(persistReferenceEntity(signature.getWho(), referenceService, requestOperation));

        // WhoTarget
        signature.setWhoTarget(persistResourceEntity(signature.getWhoTarget(), resourceService, requestOperation));

        // OnBehalfOf
        signature.setOnBehalfOf(persistReferenceEntity(signature.getOnBehalfOf(), referenceService, requestOperation));

        // OnBehalfOfTarget
        signature.setOnBehalfOfTarget(persistResourceEntity(signature.getOnBehalfOfTarget(), resourceService, requestOperation));

        // TargetFormat
        signature.setTargetFormat(persistCodeTypeEntity(signature.getTargetFormat(), codeTypeService, requestOperation));

        // SigFormat
        signature.setSigFormat(persistCodeTypeEntity(signature.getSigFormat(), codeTypeService, requestOperation));

        signature.setExtension(extensions);
        signature.setType(types);
    }

}
