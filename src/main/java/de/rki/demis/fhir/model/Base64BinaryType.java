package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.classes.PrimitiveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Base64BinaryType extends PrimitiveType<byte[]> implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    private byte[] myValue;
}
