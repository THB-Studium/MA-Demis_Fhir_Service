package de.rki.demis.fhir.model;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import de.rki.demis.fhir.util.fhir_object.classes.Element;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BackboneElement extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = -1431673179L;


    @OneToMany(fetch = FetchType.EAGER)
    @Child(
            name = "modifierExtension",
            type = {org.hl7.fhir.r4.model.Extension.class},
            order = 0, max = Child.MAX_UNLIMITED, modifier = true, summary = true)
    @Description(
            shortDefinition = "Extensions that cannot be ignored even if unrecognized",
            value = "May be used to represent additional information that is not part of " +
                    "the basic definition of the element and that modifies the understanding " +
                    "of the element in which it is contained and/or the understanding of the " +
                    "containing element's descendants. Usually modifier elements provide negation " +
                    "or qualification. To make the use of extensions safe and manageable, there is " +
                    "a strict set of governance applied to the definition and use of extensions. " +
                    "Though any implementer can define an extension, there is a set of requirements " +
                    "that SHALL be met as part of the definition of the extension. Applications processing " +
                    "a resource are required to check for modifier extensions.\n\nModifier extensions SHALL NOT " +
                    "change the meaning of any elements on Resource or DomainResource (including cannot change the " +
                    "meaning of modifierExtension itself).")
    private Set<Extension> modifierExtension;
}
