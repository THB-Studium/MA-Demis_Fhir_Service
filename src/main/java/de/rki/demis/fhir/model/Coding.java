package de.rki.demis.fhir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Coding extends Type implements Serializable {
    @Serial
    private static final long serialVersionUID = -1417514061L;


    /***
     * shortDefinition = "Identity of the terminology system",
     *           value = "The identification of the code system that defines the meaning of the symbol in the code."
     ***/
    @OneToOne
    private UriType system; // xs:anyURI - JSON string - a URI - Regex: \S*

    /***
     * shortDefinition = "Version of the system - if relevant",
     *           value = "The version of the code system which was used when choosing this code. Note that a well-maintained
     *                    code system does not need the version reported, because the meaning of codes is consistent across versions.
     *                    However this cannot consistently be assured, and when the meaning is not guaranteed to be consistent,
     *                    the version SHOULD be exchanged."
     ***/
    private String version;

    /***
     * shortDefinition = "Symbol in syntax defined by the system",
     *           value = "A symbol in syntax defined by the system. The symbol may be a predefined code or an expression
     *                    in a syntax defined by the coding system (e.g. post-coordination)."
     ***/
    @OneToOne
    private CodeType code; // xs:token - JSON string - Regex: [^\s]+(\s[^\s]+)*

    /***
     * shortDefinition = "Representation defined by the system",
     *           value = "A representation of the meaning of the code in the system, following the rules of the system."
     ***/
    private String display;

    /***
     * shortDefinition = "If this coding was chosen directly by the user",
     *           value = "Indicates that this coding was chosen by a user directly - e.g. off a pick list of available
     *                    items (codes or displays)."
     ***/
    private Boolean userSelected;
}
