package de.rki.demis.fhir.service.model;

import de.rki.demis.fhir.exception.ParsingException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.exception.ResourceNotFoundException;
import de.rki.demis.fhir.model.BinaryMod;
import de.rki.demis.fhir.repository.BinaryRepository;
import de.rki.demis.fhir.search.criteria.BinaryCriteria;
import de.rki.demis.fhir.search.specs.BinarySpecs;
import de.rki.demis.fhir.service.utils.FhirParserService;
import de.rki.demis.fhir.transfert.binary.Binary2BinaryMod;
import lombok.RequiredArgsConstructor;
import org.hl7.fhir.r4.model.Binary;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class BinaryService {
    private final BinaryRepository repository;
    private final MetaService metaService;
    private final FhirParserService fhirParserService;


    public List<BinaryMod> listAll() {
        return repository.findAll();
    }

    public BinaryMod getOne(UUID binaryModId) {
        Optional<BinaryMod> binaryOp = repository.findById(binaryModId);

        if (binaryOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("A Binary with 'id = %s' does not exist", binaryModId)
            );
        }

        return binaryOp.get();
    }

    public BinaryMod create(@NotNull BinaryMod newBinaryMod) {
        newBinaryMod.setMeta(metaService.create(newBinaryMod.getMeta()));
        newBinaryMod.setId(null);
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

    public List<BinaryMod> search(BinaryCriteria criteria) {
        return repository.findAll(new BinarySpecs(criteria));
    }

    private void checkForUniqueness(@NotNull BinaryMod binary) {
        if (repository.existsById(binary.getId())) {
            throw new ResourceBadRequestException(
                    String.format("A BinaryMod with the id=%s already exist", binary.getId())
            );
        }
    }

}
