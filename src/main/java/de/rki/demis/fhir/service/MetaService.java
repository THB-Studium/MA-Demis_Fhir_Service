package de.rki.demis.fhir.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import de.rki.demis.fhir.model.CanonicalType;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Meta;
import de.rki.demis.fhir.repository.MetaRepository;
import de.rki.demis.fhir.util.constant.RequestOperation;
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

import static de.rki.demis.fhir.util.constant.Constants.NOT_EXIST_MSG;
import static de.rki.demis.fhir.util.service.PersistenceService.persistEntity;
import static de.rki.demis.fhir.util.service.CheckForUniquenessService.checkForUniqueness;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class MetaService implements BaseService<Meta> {
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
            throw new ResourceNotFoundException(String.format(NOT_EXIST_MSG, Meta.class.getSimpleName(), metaId));
        }

        return metaOp.get();
    }

    public Meta create(@NotNull Meta newMeta) {
        checkForUniqueness(newMeta, repository);
        persistMetaComponents(newMeta, RequestOperation.Create);
        newMeta.setId(null);
        return repository.save(newMeta);
    }

    public Meta update(@NotNull UUID metaId, @NotNull Meta update) throws ResourceNotFoundException {
        getOne(metaId); // to check if the update exist
        persistMetaComponents(update, RequestOperation.Update);
        update.setId(metaId);
        return repository.save(update);
    }

    public void delete(UUID metaId) {
        getOne(metaId);
        repository.deleteById(metaId);
    }

    @Override
    public JpaRepository<?, UUID> getRepository() {
        return repository;
    }

    private void persistMetaComponents(@NotNull Meta meta, RequestOperation requestOperation) {
        Set<CanonicalType> profile = new HashSet<>();
        Set<Coding> security = new HashSet<>();
        Set<Coding> tag = new HashSet<>();

        // Source
        meta.setSource(persistEntity(meta.getSource(), uriTypeService, requestOperation));

        // Profile
        if (Objects.nonNull(meta.getProfile())) {
            meta.getProfile().forEach(item -> profile.add(persistEntity(item, canonicalTypeService, requestOperation)));
        }

        // Security
        if (Objects.nonNull(meta.getSecurity())) {
            meta.getSecurity().forEach(item -> security.add(persistEntity(item, codingService, requestOperation)));
        }

        // Tag
        if (Objects.nonNull(meta.getTag())) {
            meta.getTag().forEach(item -> tag.add(persistEntity(item, codingService, requestOperation)));
        }


        meta.setProfile(profile);
        meta.setSecurity(security);
        meta.setTag(tag);
    }

}
