package de.rki.demis.fhir.service;

import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.exception.ResourceNotFoundException;
import de.rki.demis.fhir.model.Meta;
//import de.rki.demis.fhir.repository.MetaRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class MetaService {
//    private final MetaRepository repository;
//    private final FhirParserService fhirParserService;
//
//
//    public List<Meta> listAll() {
//        return repository.findAll();
//    }
//
//    public Meta getOne(UUID metaId) {
//        Optional<Meta> metaOp = repository.findById(metaId);
//
//        if (metaOp.isEmpty()) {
//            throw new ResourceNotFoundException(
//                    String.format("A Meta with 'id = %s' does not exist", metaId)
//            );
//        }
//
//        return metaOp.get();
//    }
//
//    public Meta create(Meta newMeta) {
//        checkForUniqueness(newMeta);
//        newMeta.setId(null);
//        return repository.save(newMeta);
//    }
//
//    public void update(UUID metaId, @NotNull Meta update)
//            throws ResourceNotFoundException {
//
//        Meta metaFound = getOne(metaId);
//
//        if (!Objects.equals(metaFound.getId(), update.getId())) {
//            checkForUniqueness(update);
//        }
//
//        update.setId(metaId);
//        repository.save(update);
//    }
//
//    public void delete(UUID metaId) {
//        getOne(metaId);
//        repository.deleteById(metaId);
//    }
//
//    private void checkForUniqueness(@NotNull Meta meta) {
//        if (repository.existsById(meta.getId())) {
//            throw new ResourceBadRequestException(
//                    String.format("A Meta with the id=%s already exist", meta.getId())
//            );
//        }
//    }

}
