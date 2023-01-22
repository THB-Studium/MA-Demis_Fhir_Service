package de.rki.demis.fhir.model.udt;

import de.rki.demis.fhir.util.fhir_object.classes.Element;
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
public class Type extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 4623040030733049991L;
}
