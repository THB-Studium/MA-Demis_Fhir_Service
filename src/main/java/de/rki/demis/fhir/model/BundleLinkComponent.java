package de.rki.demis.fhir.model;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

import static de.rki.demis.fhir.util.constant.Constants.NOT_NULL_MSG;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
public class BundleLinkComponent extends BackboneElement implements Serializable {
    @Serial
    private static final long serialVersionUID = -1010386066L;


    @NotNull(message = "The 'relation' " + NOT_NULL_MSG)
    @Child(name = "relation", type = {org.hl7.fhir.r4.model.StringType.class},
            order = 1, min = 1, summary = true)
    @Description(
            shortDefinition = "See http://www.iana.org/assignments/link-relations/link-relations.xhtml#link-relations-1",
            value = "A name which details the functional use for this link - see " +
                    "[http://www.iana.org/assignments/link-relations/link-relations.xhtml#link-relations-1]" +
                    "(http://www.iana.org/assignments/link-relations/link-relations.xhtml#link-relations-1).")
    private String relation;

    @OneToOne
    @NotNull(message = "The 'url' " + NOT_NULL_MSG)
    @Child(name = "url", type = {org.hl7.fhir.r4.model.UriType.class},
            order = 2, min = 1, summary = true)
    @Description(
            shortDefinition = "Reference details for the link",
            value = "The reference details for the link.")
    private UriType url;

}
