package de.rki.demis.fhir.model.udt;

import de.rki.demis.fhir.util.fhir_object.classes.PrimitiveType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.io.Serial;
import java.io.Serializable;


@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@UserDefinedType(value = "UriTypeUDT")
public class UriType extends PrimitiveType<String> implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
}
