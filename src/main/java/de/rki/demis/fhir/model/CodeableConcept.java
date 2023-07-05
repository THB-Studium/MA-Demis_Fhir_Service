package de.rki.demis.fhir.model;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import de.rki.demis.fhir.util.fhir_object.classes.Element;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hl7.fhir.r4.model.StringType;

import javax.persistence.Entity;
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
public class CodeableConcept extends Element implements Serializable, BaseEntity {
    @Serial
    private static final long serialVersionUID = 760353246L;


    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Child(
            name = "coding", type = {org.hl7.fhir.r4.model.Coding.class},
            order = 0, max = Child.MAX_UNLIMITED, summary = true)
    @Description(
            shortDefinition = "Code defined by a terminology system",
            value = "A reference to a code defined by a terminology system.")
    private Set<Coding> coding;

    @Child(
            name = "text", type = {StringType.class},
            order = 1, summary = true)
    @Description(
            shortDefinition = "Plain text representation of the concept",
            value = "A human language representation of the concept as seen/selected/uttered by the user who " +
                    "entered the data and/or which represents the intended meaning of the user.")
    private String text;
}
