package de.rki.demis.fhir.model.udt;

import de.rki.demis.fhir.util.fhir_object.classes.BackboneElement;
import de.rki.demis.fhir.util.fhir_object.classes.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import static de.rki.demis.fhir.util.constant.Constants.NOT_EMPTY_MSG;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@UserDefinedType(value = "BundleEntryResponseComponentUDT")
public class BundleEntryResponseComponent extends BackboneElement implements Serializable {
    @Serial
    private static final long serialVersionUID = 923278008L;


    /***
     * shortDefinition = "Status response code (text optional)",
     *           value = "The status code returned by processing this entry. The status SHALL start with a 3 digit
     *                    HTTP code (e.g. 404) and may contain the standard HTTP description associated with the status code."
     ***/
    @NotEmpty(message = "The 'status' " + NOT_EMPTY_MSG)
    private String status;

    /***
     * shortDefinition = "The location (if the operation returns a location)",
     *           value = "The location header created by processing this operation, populated if the
     *                    operation returns a location."
     ***/
    private UriType location;

    /***
     * shortDefinition = "The Etag for the resource (if relevant)",
     *           value = "The Etag for the resource, if the operation for the entry produced a versioned resource
     *                    (see [Resource Metadata and Versioning](http.html#versioning) and
     *                    [Managing Resource Contention](http.html#concurrency))."
     ***/
    private String etag;

    /***
     * shortDefinition = "Server's date time modified",
     *           value = "The date/time that the resource was modified on the server."
     ***/
    private Date lastModified;

    /***
     * shortDefinition = "OperationOutcome with hints and warnings (for batch/transaction)",
     *           value = "An OperationOutcome containing hints and warnings produced as part of
     *                    processing this entry in a batch or transaction."
     ***/
    @CassandraType(type = CassandraType.Name.UDT, userTypeName = "resource_udt")
    private Resource outcome;
}
