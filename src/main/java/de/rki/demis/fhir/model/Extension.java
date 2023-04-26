package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.classes.BaseExtension;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

import static de.rki.demis.fhir.util.constant.Constants.NOT_EMPTY_MSG;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Extension extends BaseExtension implements Serializable, BaseEntity {
    @Serial
    private static final long serialVersionUID = 194602931L;

    /***
     * shortDefinition = "identifies the meaning of the extension",
     *           value = "Source of the definition for the extension code - a logical name or a URL."
     ***/
    @OneToOne
    @NotEmpty(message = "The 'url' " + NOT_EMPTY_MSG)
    private UriType url;

    /***
     * shortDefinition = "Value of extension",
     *           value = "Value of extension - must be one of a constrained set of the data types
     *                    (see [Extensibility](extensibility.html) for a list)."
     ***/
    @OneToOne
    private Type value;
}
