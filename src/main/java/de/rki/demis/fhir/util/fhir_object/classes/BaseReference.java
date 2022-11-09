package de.rki.demis.fhir.util.fhir_object.classes;

import de.rki.demis.fhir.model.Type;
import de.rki.demis.fhir.util.fhir_object.interfaces.IBaseResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serial;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class BaseReference extends Type implements Serializable {
    @Serial
    private static final long serialVersionUID = 4623040030049991L;


    @Transient
    private IBaseResource resource;
}
