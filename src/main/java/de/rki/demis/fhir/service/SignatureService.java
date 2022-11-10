package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.model.Signature;
import de.rki.demis.fhir.repository.CodingRepository;
import de.rki.demis.fhir.repository.ExtensionRepository;
import de.rki.demis.fhir.repository.SignatureRepository;
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

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class SignatureService {
    private final SignatureRepository repository;
    private final ExtensionRepository extensionRepository;
    private final CodingRepository codingRepository;
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
        // Extension
        Set<Extension> extension = new HashSet<>();
        newSignature.getExtension().forEach(item -> {
            if (Objects.isNull(item.getId()) || !extensionRepository.existsById(item.getId())) {
                item = extensionRepository.save(item);
            }
            extension.add(item);
        });

        // Type
        Set<Coding> type = new HashSet<>();
        newSignature.getType().forEach(item -> {
            if (Objects.isNull(item.getId()) || !codingRepository.existsById(item.getId())) {
                item = codingRepository.save(item);
            }
            type.add(item);
        });

        // Who
        if (Objects.nonNull(newSignature.getWho())) {
            newSignature.setWho(referenceService.create(newSignature.getWho()));
        }

        // WhoTarget
        if (Objects.nonNull(newSignature.getWhoTarget())) {
            newSignature.setWhoTarget(resourceService.create(newSignature.getWhoTarget()));
        }

        // OnBehalfOf
        if (Objects.nonNull(newSignature.getOnBehalfOf())) {
            newSignature.setOnBehalfOf(referenceService.create(newSignature.getOnBehalfOf()));
        }

        // OnBehalfOfTarget
        if (Objects.nonNull(newSignature.getOnBehalfOfTarget())) {
            newSignature.setOnBehalfOfTarget(resourceService.create(newSignature.getOnBehalfOfTarget()));
        }

        // TargetFormat
        if (Objects.nonNull(newSignature.getTargetFormat())) {
            newSignature.setTargetFormat(codeTypeService.create(newSignature.getTargetFormat()));
        }

        // SigFormat
        if (Objects.nonNull(newSignature.getSigFormat())) {
            newSignature.setSigFormat(codeTypeService.create(newSignature.getSigFormat()));
        }

        newSignature.setType(type);
        newSignature.setId(null);
        return repository.save(newSignature);
    }

    public void update(UUID signatureId, @NotNull Signature update) throws ResourceNotFoundException {
        Signature signatureFound = getOne(signatureId);

        if (!Objects.equals(signatureFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

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

}
