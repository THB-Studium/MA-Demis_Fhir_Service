package de.rki.demis.fhir.model;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import de.rki.demis.fhir.util.fhir_object.classes.Element;
import de.rki.demis.fhir.util.fhir_object.enums.IdentifierUse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.StringType;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serial;
import java.io.Serializable;
import java.time.Period;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Identifier extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = -478840981L;


    @OneToOne
    @Child(name = "use", type = {CodeType.class}, order = 0, modifier = true, summary = true)
    @Description(
            shortDefinition = "usual | official | temp | secondary | old (If known)",
            value = "The purpose of this identifier.")
    private Enumeration<IdentifierUse> use; // xs:token - JSON string - Regex: [^\s]+(\s[^\s]+)*

    @OneToOne
    @Child(name = "type", type = {org.hl7.fhir.r4.model.CodeableConcept.class},
            order = 1, summary = true)
    @Description(
            shortDefinition = "Description of identifier",
            value = "A coded type for the identifier that can be used to determine which identifier to use for a specific purpose.")
    private CodeableConcept type;

    @OneToOne
    @Child(name = "system", type = {org.hl7.fhir.r4.model.UriType.class},
            order = 2, summary = true)
    @Description(
            shortDefinition = "The namespace for the identifier value",
            value = "Establishes the namespace for the value - that is, a URL that describes a set values that are unique.")
    private UriType system; // xs:anyURI - 	A JSON string - a URI - Regex: \S*

    @Child(name = "value", type = {StringType.class}, order = 3, summary = true)
    @Description(
            shortDefinition = "The value that is unique",
            value = "The portion of the identifier typically relevant to the user and which is unique within the context of the system.")
    private String value;

    @Child(name = "period", type = {org.hl7.fhir.r4.model.Period.class},
            order = 4, summary = true)
    @Description(shortDefinition = "Time period when id is/was valid for use",
            value = "Time period during which identifier is/was valid for use.")
    private Period period;

    @OneToOne
    @Child(name = "assigner", type = {Organization.class}, order = 5, summary = true)
    @Description(
            shortDefinition = "Organization that issued id (may be just text)",
            value = "Organization that issued/manages the identifier.")
    private Reference assigner;
}
