package de.rki.demis.fhir.model.udt;

import de.rki.demis.fhir.util.fhir_object.classes.Element;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@UserDefinedType(value = "CodeableConceptUDT")
public class CodeableConcept extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 760353246L;


    /***
     * shortDefinition = "Code defined by a terminology system",
     *           value = "A reference to a code defined by a terminology system."
     ***/
    private Set<Coding> coding;

    /***
     * shortDefinition = "Plain text representation of the concept",
     *           value = "A human language representation of the concept as seen/selected/uttered by the user who entered
     *                    the data and/or which represents the intended meaning of the user."
     ***/
    private String text;
}
