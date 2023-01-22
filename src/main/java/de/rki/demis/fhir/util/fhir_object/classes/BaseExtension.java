package de.rki.demis.fhir.util.fhir_object.classes;

import de.rki.demis.fhir.model.udt.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;


@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class BaseExtension extends Type implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
