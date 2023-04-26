package de.rki.demis.fhir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class BundleEntryComponent extends BackboneElement implements Serializable, BaseEntity {
    @Serial
    private static final long serialVersionUID = 517783054L;


    /***
     * shortDefinition = "Links related to this entry",
     *           value = "A series of links that provide context to this entry."
     ***/
    @OneToMany(fetch = FetchType.EAGER)
    private Set<BundleLinkComponent> link;

    /***
     * shortDefinition = "URI for resource (Absolute URL server address or URI for UUID/OID)",
     *           value = "The Absolute URL for the resource.  The fullUrl SHALL NOT disagree with the id in the resource -
     *                    i.e. if the fullUrl is not an urn:uuid, the URL shall be version-independent URL consistent with
     *                    the Resource.id. The fullUrl is a version independent reference to the resource. The fullUrl element
     *                    SHALL have a value except that: fullUrl can be empty on a POST (although it does not need to
     *                    when specifying a temporary id for reference in the bundle) Results from operations might involve
     *                    resources that are not identified."
     ***/
    @OneToOne
    private UriType fullUrl;

    /***
     * shortDefinition = "A resource in the bundle",
     *           value = "The Resource for the entry. The purpose/meaning of the resource is determined by the Bundle.type."
     ***/
    @OneToOne
    private Resource resource;

    /***
     * shortDefinition = "Search related information",
     *           value = "Information about the search process that lead to the creation of this entry."
     ***/
    @OneToOne
    private BundleEntrySearchComponent search;

    /***
     * shortDefinition = "Additional execution information (transaction/batch/history)",
     *           value = "Additional information about how this entry should be processed as part of a transaction or batch.
     *                    For history, it shows how the entry was processed to create the version contained in the entry."
     ***/
    @OneToOne
    private BundleEntryRequestComponent request;

    /***
     * shortDefinition = "Results of execution (transaction/batch/history)",
     *           value = "Indicates the results of processing the corresponding 'request' entry in the batch or transaction
     *                    being responded to or what the results of an operation where when returning history."
     ***/
    @OneToOne
    private BundleEntryResponseComponent response;
}

