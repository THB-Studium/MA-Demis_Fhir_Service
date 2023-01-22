package de.rki.demis.fhir.model;

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


    /***
     * shortDefinition = "See http://www.iana.org/assignments/link-relations/link-relations.xhtml#link-relations-1",
     *           value = "A name which details the functional use for this link - see
     *                    [http://www.iana.org/assignments/link-relations/link-relations.xhtml#link-relations-1]
     *                    (http://www.iana.org/assignments/link-relations/link-relations.xhtml#link-relations-1)."
     ***/
    @NotNull(message = "The 'relation' " + NOT_NULL_MSG)
    private String relation;

    /***
     * shortDefinition = "Reference details for the link",
     *           value = "The reference details for the link."
     ***/
    @OneToOne
    @NotNull(message = "The 'url' " + NOT_NULL_MSG)
    private UriType url;

}
