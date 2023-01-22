package de.rki.demis.fhir.model.udt;

import de.rki.demis.fhir.util.fhir_object.classes.PrimitiveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.io.Serial;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@UserDefinedType(value = "CodeTypeUDT")
public class CodeType extends PrimitiveType<String> implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    private String system;
}
