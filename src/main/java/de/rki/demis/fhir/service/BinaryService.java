package de.rki.demis.fhir.service;

import de.rki.demis.fhir.exception.ParsingException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.exception.ResourceNotFoundException;
import de.rki.demis.fhir.model.BinaryMod;
import de.rki.demis.fhir.repository.BinaryRepository;
import de.rki.demis.fhir.transfert.binary.Binary2BinaryMod;
import de.rki.demis.fhir.util.service.FhirParserService;
import lombok.RequiredArgsConstructor;
import org.hl7.fhir.r4.model.Binary;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BinaryService {
    private final BinaryRepository repository;
    private final MetaService metaService;
    private final UriTypeService uriTypeService;
    private final CodeTypeService codeTypeService;
    private final ReferenceService referenceService;
    private final ResourceService resourceService;
    private final FhirParserService fhirParserService;


    public List<BinaryMod> listAll() {
        return repository.findAll();
    }

    public BinaryMod getOne(UUID binaryModId) {
        Optional<BinaryMod> binaryOp = repository.findById(binaryModId);

        if (binaryOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A Binary with 'id = %s' does not exist :::", binaryModId)
            );
        }

        return binaryOp.get();
    }

    public BinaryMod create(@NotNull BinaryMod newBinaryMod) {
        // Meta
        if (Objects.nonNull(newBinaryMod.getMeta())) {
            newBinaryMod.setMeta(metaService
                    .create(newBinaryMod.getMeta()));
        }

        // ImplicitRules
        if (Objects.nonNull(newBinaryMod.getImplicitRules())) {
            newBinaryMod.setImplicitRules(uriTypeService
                    .create(newBinaryMod.getImplicitRules()));
        }

        // Language
        if (Objects.nonNull(newBinaryMod.getLanguage())) {
            newBinaryMod.setLanguage(codeTypeService
                    .create(newBinaryMod.getLanguage()));
        }

        // SecurityContext
        if (Objects.nonNull(newBinaryMod.getSecurityContext())) {
            newBinaryMod.setSecurityContext(referenceService
                    .create(newBinaryMod.getSecurityContext()));
        }

        // SecurityContextTarget
        if (Objects.nonNull(newBinaryMod.getSecurityContextTarget())) {
            newBinaryMod.setSecurityContextTarget(resourceService
                    .create(newBinaryMod.getSecurityContextTarget()));
        }

        return repository.save(newBinaryMod);
    }

    public void update(UUID binaryId, String updateString, MediaType mediaType)
            throws ResourceNotFoundException, ParsingException {

        BinaryMod binaryModFound = getOne(binaryId);
        Binary binary = fhirParserService.parseBinary(updateString, mediaType);
        BinaryMod update = Objects.requireNonNull(Binary2BinaryMod.apply(binary));

        if (!Objects.equals(binaryModFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(binaryId);
        repository.save(update);
    }

    public void delete(UUID binaryId) {
        getOne(binaryId);
        repository.deleteById(binaryId);
    }

//    public List<BinaryMod> search(BinaryCriteria criteria) {
//        return repository.findAll(new BinarySpecs(criteria));
//    }

    private void checkForUniqueness(@NotNull BinaryMod binary) {
        if (repository.existsById(binary.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BinaryMod with the id=%s already exist :::", binary.getId())
            );
        }
    }

}
