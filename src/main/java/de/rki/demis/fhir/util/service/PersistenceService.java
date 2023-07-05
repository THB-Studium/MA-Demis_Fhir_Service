package de.rki.demis.fhir.util.service;

import de.rki.demis.fhir.model.BaseEntity;
import de.rki.demis.fhir.service.BaseService;
import de.rki.demis.fhir.util.constant.RequestOperation;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class PersistenceService {

    public static <T extends BaseEntity> T persistEntity(T newEntity, BaseService<T> service, RequestOperation requestOperation) {
        if (Objects.nonNull(newEntity)) {
            newEntity = Objects.equals(requestOperation, RequestOperation.Update) && Objects.nonNull(newEntity.getId()) // to create directly the entity if id = null
                    ? service.getRepository().existsById(newEntity.getId()) // to update the entity if already exist or create it when not
                        ? service.update(newEntity.getId(), newEntity)
                        : service.create(newEntity)
                    : service.create(newEntity);
        }
        return newEntity;
    }

}
