package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.enums.HTTPVerb;
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
import java.util.Date;

import static de.rki.demis.fhir.util.constant.Constants.NOT_NULL_MSG;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class BundleEntryRequestComponent extends BackboneElement implements Serializable, BaseEntity {
    @Serial
    private static final long serialVersionUID = -1349769744L;


    /***
     * shortDefinition = "GET | HEAD | POST | PUT | DELETE | PATCH",
     *           value = "In a transaction or batch, this is the HTTP action to be executed for this entry. In a history
     *                    bundle, this indicates the HTTP action that occurred."
     ***/
    @OneToOne
    @NotNull(message = "The 'method' " + NOT_NULL_MSG)
    private Enumeration<HTTPVerb> method;

    /***
     * shortDefinition = "URL for HTTP equivalent of this entry",
     *           value = "The URL for this entry, relative to the root (the address to which the request is posted)."
     ***/
    @OneToOne
    @NotNull(message = "The 'url' " + NOT_NULL_MSG)
    private UriType url;

    /***
     * shortDefinition = "For managing cache currency",
     *           value = "If the ETag values match, return a 304 Not Modified status.
     *                    See the API documentation for [\"Conditional Read\"](http.html#cread)."
     ***/
    private String ifNoneMatch;

    /***
     * shortDefinition = "For managing cache currency",
     *           value = "Only perform the operation if the last updated date matches. See the API documentation
     *                    for [\"Conditional Read\"](http.html#cread)."
     ***/
    private Date ifModifiedSince;

    /***
     * shortDefinition = "For managing update contention",
     *           value = "Only perform the operation if the Etag value matches. For more information, see the API
     *                    section [\"Managing Resource Contention\"](http.html#concurrency)."
     ***/
    private String ifMatch;

    /***
     * shortDefinition = "For conditional creates",
     *           value = "Instruct the server not to perform the create if a specified resource already exists.
     *                    For further information, see the API documentation for [\"Conditional Create\"](http.html#ccreate).
     *                    This is just the query portion of the URL - what follows the \"?\" (not including the \"?\")."
     ***/
    private String ifNoneExist;
}
