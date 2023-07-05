package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.classes.Element;
import de.rki.demis.fhir.util.fhir_object.enums.IdentifierUse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class Identifier extends Element implements Serializable, BaseEntity {
    @Serial
    private static final long serialVersionUID = -478840981L;


    /***
     * shortDefinition = "usual | official | temp | secondary | old (If known)",
     *           value = "The purpose of this identifier."
     ***/
    @OneToOne
    private Enumeration<IdentifierUse> use; // xs:token - JSON string - Regex: [^\s]+(\s[^\s]+)*

    /***
     * shortDefinition = "Description of identifier",
     *           value = "A coded type for the identifier that can be used to determine which identifier to use for a specific purpose."
     ***/
    @OneToOne
    private CodeableConcept type;

    /***
     * shortDefinition = "The namespace for the identifier value",
     *           value = "Establishes the namespace for the value - that is, a URL that describes a set values that are unique."
     ***/
    @OneToOne
    private UriType system; // xs:anyURI - 	A JSON string - a URI - Regex: \S*

    /***
     * shortDefinition = "The value that is unique",
     *           value = "The portion of the identifier typically relevant to the user and which is unique within
     *                    the context of the system."
     ***/
    private String value;

    /***
     * shortDefinition = "Time period when id is/was valid for use",
     *           value = "Time period during which identifier is/was valid for use."
     ***/
    private Period period;

    /***
     * shortDefinition = "Organization that issued id (may be just text)",
     *           value = "Organization that issued/manages the identifier."
     ***/
    @OneToOne
    private Reference assigner;
}
