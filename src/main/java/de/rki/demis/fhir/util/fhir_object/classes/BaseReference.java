package de.rki.demis.fhir.util.fhir_object.classes;

import de.rki.demis.fhir.model.udt.Type;
import de.rki.demis.fhir.util.fhir_object.interfaces.IBaseResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import java.io.Serial;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class BaseReference extends Type implements Serializable {
    @Serial
    private static final long serialVersionUID = 4623040030049991L;

    @Transient
    private IBaseResource resource;
}
