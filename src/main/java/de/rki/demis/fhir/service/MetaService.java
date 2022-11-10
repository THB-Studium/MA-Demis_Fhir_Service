package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.CanonicalType;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Meta;
import de.rki.demis.fhir.model.UriType;
import de.rki.demis.fhir.repository.CanonicalTypeRepository;
import de.rki.demis.fhir.repository.CodingRepository;
import de.rki.demis.fhir.repository.MetaRepository;
import de.rki.demis.fhir.repository.UriTypeRepository;
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
public class MetaService {
    private final MetaRepository repository;
    private final UriTypeRepository uriTypeRepository;
    private final CanonicalTypeRepository canonicalTypeRepository;
    private final CodingRepository codingRepository;
    private final CodingService codingService;



    public List<Meta> listAll() {
        return repository.findAll();
    }

    public Meta getOne(UUID metaId) {
        Optional<Meta> metaOp = repository.findById(metaId);

        if (metaOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A Meta with 'id = %s' does not exist :::", metaId)
            );
        }

        return metaOp.get();
    }

    public Meta create(@NotNull Meta newMeta) {
        if (Objects.isNull(newMeta.getId()) || !repository.existsById(newMeta.getId())) {

            // Source
            UriType source = newMeta.getSource();
            if (Objects.isNull(source.getId()) || !uriTypeRepository.existsById(source.getId())) {
                source = uriTypeRepository.save(source);
            }

            // Profile
            Set<CanonicalType> profile = new HashSet<>();
            newMeta.getProfile().forEach(item -> {
                if (Objects.isNull(item.getId()) || !canonicalTypeRepository.existsById(item.getId())) {
                    item = canonicalTypeRepository.save(item);
                }
                profile.add(item);
            });

            // Security
            Set<Coding> security = new HashSet<>();
            newMeta.getSecurity().forEach(item -> {
                if (Objects.isNull(item.getId()) || !codingRepository.existsById(item.getId())) {
                    item = codingService.create(item);
                }
                security.add(item);
            });

            // Tag
            Set<Coding> tag = new HashSet<>();
            newMeta.getTag().forEach(item -> {
                if (Objects.isNull(item.getId()) || !codingRepository.existsById(item.getId())) {
                    item = codingService.create(item);
                }
                tag.add(item);
            });

            newMeta.setSource(source);
            newMeta.setProfile(profile);
            newMeta.setSecurity(security);
            newMeta.setTag(tag);
            newMeta.setId(null);
        }

        return repository.save(newMeta);
    }

    public void update(UUID metaId, @NotNull Meta update)
            throws ResourceNotFoundException {

        Meta metaFound = getOne(metaId);

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

    private void checkForUniqueness(@NotNull Meta meta) {
        if (repository.existsById(meta.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A Meta with the id=%s already exist :::", meta.getId())
            );
        }
    }

}
