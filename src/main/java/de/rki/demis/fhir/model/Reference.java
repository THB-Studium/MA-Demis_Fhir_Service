package de.rki.demis.fhir.model;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import de.rki.demis.fhir.model.Identifier;
import de.rki.demis.fhir.model.UriType;
import de.rki.demis.fhir.util.fhir_object.classes.BaseReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hl7.fhir.r4.model.StringType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.io.Serial;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Reference extends BaseReference implements Serializable {
    @Serial
    private static final long serialVersionUID = 784245805L;

    @Child(
            name = "reference",
            type = {StringType.class},
            order = 0,
            summary = true
    )
    @Description(
            shortDefinition = "Literal reference, Relative, internal or absolute URL",
            value = "A reference to a location at which the other resource is found. The reference may be a relative reference, in which case it is relative to the service base URL, or an absolute URL that resolves to the location where the resource is found. The reference may be version specific or not. If the reference is not to a FHIR RESTful server, then it should be assumed to be version specific. Internal fragment references (start with '#') refer to contained resources."
    )
    protected String reference;
    @OneToOne
    @Child(
            name = "type",
            type = {org.hl7.fhir.r4.model.UriType.class},
            order = 1,
            summary = true
    )
    @Description(
            shortDefinition = "Type the reference refers to (e.g. \"Patient\")",
            value = "The expected type of the target of the reference. If both Reference.type and Reference.reference are populated and Reference.reference is a FHIR URL, both SHALL be consistent.\n\nThe type is the Canonical URL of Resource Definition that is the type this reference refers to. References are URLs that are relative to https://hl7.org/fhir/StructureDefinition/ e.g. \"Patient\" is a reference to https://hl7.org/fhir/StructureDefinition/Patient. Absolute URLs are only allowed for logical models (and can only be used in references in logical models, not resources)."
    )
    protected UriType type;
    @OneToOne
    @Child(
            name = "identifier",
            type = {org.hl7.fhir.r4.model.Identifier.class},
            order = 2,
            summary = true
    )
    @Description(
            shortDefinition = "Logical reference, when literal reference is not known",
            value = "An identifier for the target resource. This is used when there is no way to reference the other resource directly, either because the entity it represents is not available through a FHIR server, or because there is no way for the author of the resource to convert a known identifier to an actual location. There is no requirement that a Reference.identifier point to something that is actually exposed as a FHIR instance, but it SHALL point to a business concept that would be expected to be exposed as a FHIR instance, and that instance would need to be of a FHIR resource type allowed by the reference."
    )
    protected Identifier identifier;
    @Child(
            name = "display",
            type = {StringType.class},
            order = 3,
            summary = true
    )
    @Description(
            shortDefinition = "Text alternative for the resource",
            value = "Plain text narrative that identifies the resource in addition to the resource reference."
    )
    protected String display;

}
