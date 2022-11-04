package de.rki.demis.fhir.util.fhir_object.classes;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import de.rki.demis.fhir.model.CodeType;
import de.rki.demis.fhir.model.Meta;
import de.rki.demis.fhir.model.UriType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hl7.fhir.r4.model.IdType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Resource extends BaseResource implements Serializable {
    @Serial
    private static final long serialVersionUID = -559462759L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Child(
            name = "id",
            type = {IdType.class},
            order = 0,
            summary = true
    )
    @Description(
            shortDefinition = "Logical id of this artifact",
            value = "The logical id of the resource, as used in the URL for the resource. Once assigned, this value never changes."
    )
    private UUID id;
    private String resourceType;
    @OneToOne
    @Child(
            name = "meta",
            type = {org.hl7.fhir.r4.model.Meta.class},
            order = 1,
            summary = true
    )
    @Description(
            shortDefinition = "Metadata about the resource",
            value = "The metadata about the resource. This is content that is maintained by the infrastructure. Changes to the content might not always be associated with version changes to the resource."
    )
    private Meta meta;
//    @OneToOne
//    @Child(
//            name = "implicitRules",
//            type = {org.hl7.fhir.r4.model.UriType.class},
//            order = 2,
//            modifier = true,
//            summary = true
//    )
//    @Description(
//            shortDefinition = "A set of rules under which this content was created",
//            value = "A reference to a set of rules that were followed when the resource was constructed, and which must be understood when processing the content. Often, this is a reference to an implementation guide that defines the special rules along with other profiles etc."
//    )
//    private UriType implicitRules; // xs:anyURI - 	A JSON string - a URI - Regex: \S*
//    @OneToOne
//    @Child(
//            name = "language",
//            type = {org.hl7.fhir.r4.model.CodeType.class},
//            order = 3
//    )
//    @Description(
//            shortDefinition = "Language of the resource content",
//            value = "The base language in which the resource is written."
//    )
//    private CodeType language; // xs:token - JSON string - Regex: [^\s]+(\s[^\s]+)*
}
