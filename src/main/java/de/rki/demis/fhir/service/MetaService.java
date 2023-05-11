package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.CanonicalType;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Meta;
import de.rki.demis.fhir.repository.MetaRepository;
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

import static de.rki.demis.fhir.util.service.PersistenceService.persistCanonicalTypeEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistCodingEntity;
import static de.rki.demis.fhir.util.service.PersistenceService.persistUriTypeEntity;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class MetaService {
    private final MetaRepository repository;
    private final UriTypeService uriTypeService;
    private final CanonicalTypeService canonicalTypeService;
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
        persistMetaComponents(newMeta, RequestOperation.Create);
        newMeta.setId(null);
        return repository.save(newMeta);
    }

    public void update(@NotNull UUID metaId, @NotNull Meta update) throws ResourceNotFoundException {
        // to check if the update exist
        getOne(metaId);

        // to check the uniqueness of the update
        if (!Objects.equals(metaId, update.getId())) {
            checkForUniqueness(update);
        }

        persistMetaComponents(update, RequestOperation.Update);
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

    private void persistMetaComponents(@NotNull Meta meta, RequestOperation requestOperation) {
        Set<CanonicalType> profile = new HashSet<>();
        Set<Coding> security = new HashSet<>();
        Set<Coding> tag = new HashSet<>();

        // Source
        meta.setSource(persistUriTypeEntity(meta.getSource(), uriTypeService, requestOperation));

        // Profile
        if (Objects.nonNull(meta.getProfile())) {
            meta.getProfile().forEach(item ->
                    profile.add(persistCanonicalTypeEntity(item, canonicalTypeService, requestOperation)));
        }

        // Security
        if (Objects.nonNull(meta.getSecurity())) {
            meta.getSecurity().forEach(item ->
                    security.add(persistCodingEntity(item, codingService, requestOperation)));
        }

        // Tag
        if (Objects.nonNull(meta.getTag())) {
            meta.getTag().forEach(item ->
                    tag.add(persistCodingEntity(item, codingService, requestOperation)));
        }


        meta.setProfile(profile);
        meta.setSecurity(security);
        meta.setTag(tag);
    }

}
