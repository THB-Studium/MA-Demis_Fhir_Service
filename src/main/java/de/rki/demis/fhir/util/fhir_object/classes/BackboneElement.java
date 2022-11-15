package de.rki.demis.fhir.util.fhir_object.classes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;


@Data
//@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class BackboneElement extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = -1431673179L;


//    @Child(
//            name = "modifierExtension",
//            type = {org.hl7.fhir.r4.model.Extension.class},
//            order = 0, max = Child.MAX_UNLIMITED, modifier = true, summary = true)
//    @Description(
//            shortDefinition = "Extensions that cannot be ignored even if unrecognized",
//            value = """
//                    May be used to represent additional information that is not part of the basic definition of the\040
//                    element and that modifies the understanding of the element in which it is contained and/or the\040
//                    understanding of the containing element's descendants. Usually modifier elements provide negation\040
//                    or qualification. To make the use of extensions safe and manageable, there is a strict set of\040
//                    governance applied to the definition and use of extensions. Though any implementer can define an\040
//                    extension, there is a set of requirements that SHALL be met as part of the definition of the extension.\040
//                    Applications processing a resource are required to check for modifier extensions.
//
//                    Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot change the meaning of modifierExtension itself).""")
//    private Set<Extension> modifierExtension;
}
