package de.rki.demis.fhir.util.fhir_object.classes;

import de.rki.demis.fhir.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
//@MappedSuperclass
public class PrimitiveType<T> extends Type implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    private T myCoercedValue; // todo: Generic type jpa persistent
    private String myStringValue;
}
