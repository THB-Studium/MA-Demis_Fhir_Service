package de.rki.demis.fhir.service.model;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.CodeType;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.UriType;
import de.rki.demis.fhir.repository.CodeTypeRepository;
import de.rki.demis.fhir.repository.CodingRepository;
import de.rki.demis.fhir.repository.UriTypeRepository;
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
public class CodingService {
    private final CodingRepository repository;
    private final UriTypeRepository uriTypeRepository;
    private final CodeTypeRepository codeTypeRepository;


    public List<Coding> listAll() {
        return repository.findAll();
    }

    public Coding getOne(UUID metaId) {
        Optional<Coding> metaOp = repository.findById(metaId);

        if (metaOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("A Coding with 'id = %s' does not exist", metaId)
            );
        }

        return metaOp.get();
    }

    public Coding create(@NotNull Coding newCoding) {
        if (Objects.isNull(newCoding.getId()) || !repository.existsById(newCoding.getId())) {
            UriType system = newCoding.getSystem();
            if (Objects.isNull(system.getId()) || !uriTypeRepository.existsById(system.getId())) {
                system = uriTypeRepository.saveAndFlush(system);
            }

            CodeType code = newCoding.getCode();
            if (Objects.isNull(code.getId()) || !codeTypeRepository.existsById(code.getId())) {
                code = codeTypeRepository.saveAndFlush(code);
            }

            newCoding.setSystem(system);
            newCoding.setCode(code);
//            newCoding.setId(null);
        }

        return repository.save(newCoding);
    }

    public void update(UUID metaId, @NotNull Coding update)
            throws ResourceNotFoundException {

        Coding metaFound = getOne(metaId);

        if (!Objects.equals(metaFound.getId(), update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(metaId);
        repository.save(update);
    }

    public void delete(UUID metaId) {
        getOne(metaId);
        repository.deleteById(metaId);
    }

    private void checkForUniqueness(@NotNull Coding meta) {
        if (repository.existsById(meta.getId())) {
            throw new ResourceBadRequestException(
                    String.format("A Coding with the id=%s already exist", meta.getId())
            );
        }
    }

}
