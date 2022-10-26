package de.rki.demis.fhir.model;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
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
public class Extension extends BaseExtension implements Serializable {
    @Serial
    private static final long serialVersionUID = 194602931L;

    @OneToOne
    @NotEmpty(message = "The 'url' " + NOT_EMPTY_MSG)
    @Child( name = "url",
            type = {org.hl7.fhir.r4.model.UriType.class},
            order = 0, min = 1)
    @Description(
            shortDefinition = "identifies the meaning of the extension",
            value = "Source of the definition for the extension code - a logical name or a URL.")
    private UriType url;
    @OneToOne
    @Child(name = "value", order = 1)
    @Description(
            shortDefinition = "Value of extension",
            value = "Value of extension - must be one of a constrained set of the data types (see [Extensibility](extensibility.html) for a list).")
    private Type value;
}
