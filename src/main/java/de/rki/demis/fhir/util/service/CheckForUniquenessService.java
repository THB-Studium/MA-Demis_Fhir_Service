package de.rki.demis.fhir.util.service;

import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.model.BaseEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;
import java.util.UUID;

<<<<<<< HEAD
import static de.rki.demis.fhir.util.constant.Constants.ALREADY_EXIST_MSG;

=======
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
public class CheckForUniquenessService {

    public static <T extends BaseEntity> void checkForUniqueness(@NotNull T entity, JpaRepository<T, UUID> repository) {
        if (Objects.nonNull(entity.getId()) && repository.existsById(entity.getId())) {
            throw new ResourceBadRequestException(
<<<<<<< HEAD
                    String.format(ALREADY_EXIST_MSG, entity.getClass().getSimpleName(), entity.getId())
=======
                    String.format("::: A %s with the id=%s already exists :::", entity.getClass().getSimpleName(), entity.getId())
>>>>>>> e9e3b2c (fixe update issues and some refactorings are done)
            );
        }
    }

}
