package de.rki.demis.fhir.model;

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
@UserDefinedType(value = "CanonicalType")
public class CanonicalType extends UriType implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;
}
