package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.classes.BaseExtension;
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
@UserDefinedType(value = "Extension")
public class Extension extends BaseExtension implements Serializable {
    @Serial
    private static final long serialVersionUID = 194602931L;

    /**
     * shortDefinition = "identifies the meaning of the extension",
     * value = "Source of the definition for the extension code - a logical name or a URL."
     **/
    private UriType url;

    /**
     * shortDefinition = "Value of extension",
     * value = "Value of extension - must be one of a constrained set of the data types
     * (see [Extensibility](extensibility.html) for a list)."
     **/
    private Type value;
}
