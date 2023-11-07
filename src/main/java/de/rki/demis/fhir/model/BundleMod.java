package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.enums.BundleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import static de.rki.demis.fhir.util.constant.Constants.NOT_NULL_MSG;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class BundleMod extends Resource implements Serializable, BaseEntity {
    @Serial
    private static final long serialVersionUID = 1353224198L;


    /***
     * shortDefinition = "Persistent identifier for the bundle",
     *           value = "A persistent identifier for the bundle that won't change as a bundle is copied from server to server."
     ***/
    @OneToOne
    private Identifier identifier;

    /***
     * shortDefinition = "document | message | transaction | transaction-response | batch | batch-response | history | searchset | collection",
     *           value = "Indicates the purpose of this bundle - how it is intended to be used."
     ***/
    @OneToOne
    @NotNull(message = "The 'Bundle type' " + NOT_NULL_MSG)
    private Enumeration<BundleType> type; // xs:token - JSON string - Regex: [^\s]+(\s[^\s]+)*

    /***
     * shortDefinition = "When the bundle was assembled",
     *           value = "The date/time that the bundle was assembled - i.e. when the resources were placed in the bundle."
     ***/
    private Date timestamp;

    /***
     * shortDefinition = "If search, the total number of matches",
     *           value = "If a set of search matches, this is the total number of entries of type 'match' across all pages
     *                    in the search.  It does not include search.mode = 'include' or 'outcome' entries, and it does not
     *                    provide a count of the number of entries in the Bundle."
     ***/
    private int total;

    /***
     * shortDefinition = "Links related to this Bundle",
     *           value = "A series of links that provide context to this bundle."
     ***/
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<BundleLinkComponent> link;

    /***
     * shortDefinition = "Entry in the bundle - will have a resource or information",
     *           value = "An entry in a bundle resource - will either contain a resource or information about a
     *                    resource (transactions and history only)."
     ***/
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<BundleEntryComponent> entry;

    /***
     * shortDefinition = "Digital Signature",
     *           value = "Digital Signature - base64 encoded. XML-DSig or a JWT."
     ***/
    @OneToOne
    private Signature signature;
}
