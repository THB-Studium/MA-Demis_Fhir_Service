package de.rki.demis.fhir.model.udt;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import de.rki.demis.fhir.util.fhir_object.classes.BackboneElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import static de.rki.demis.fhir.util.constant.Constants.NOT_EMPTY_MSG;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@EqualsAndHashCode(callSuper = true)
//@Table(value = "BundleEntryResponseComponent")
//public class BundleEntryResponseComponent extends BackboneElement implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 923278008L;
//
//
//    @NotEmpty(message = "The 'status' " + NOT_EMPTY_MSG)
//    @Child(name = "status",
//            type = {org.hl7.fhir.r4.model.StringType.class}, order = 1, min = 1,
//            summary = true)
//    @Description(
//            shortDefinition = "Status response code (text optional)",
//            value = "The status code returned by processing this entry. The status SHALL " +
//                    "start with a 3 digit HTTP code (e.g. 404) and may contain the standard HTTP " +
//                    "description associated with the status code.")
//    private String status;
//
//    @Child(name = "location", type = {org.hl7.fhir.r4.model.UriType.class}, order = 2, summary = true)
//    @Description(
//            shortDefinition = "The location (if the operation returns a location)",
//            value = "The location header created by processing this operation, populated if the operation returns a location.")
//    @CassandraType(type = CassandraType.Name.UDT)
//    private UriType location;
//
//    @Child(name = "etag",
//            type = {org.hl7.fhir.r4.model.StringType.class}, order = 3,
//            summary = true)
//    @Description(
//            shortDefinition = "The Etag for the resource (if relevant)",
//            value = "The Etag for the resource, if the operation for the entry produced a " +
//                    "versioned resource (see [Resource Metadata and Versioning](http.html#versioning) " +
//                    "and [Managing Resource Contention](http.html#concurrency)).")
//    private String etag;
//
//    @Child(name = "lastModified",
//            type = {org.hl7.fhir.r4.model.InstantType.class}, order = 4,
//            summary = true)
//    @Description(
//            shortDefinition = "Server's date time modified",
//            value = "The date/time that the resource was modified on the server.")
//    private Date lastModified;
//
//    @Child(name = "outcome",
//            type = {org.hl7.fhir.r4.model.Resource.class}, order = 5,
//            summary = true)
//    @Description(
//            shortDefinition = "OperationOutcome with hints and warnings (for batch/transaction)",
//            value = "An OperationOutcome containing hints and warnings produced as part of processing " +
//                    "this entry in a batch or transaction.")
//    private Resource outcome;
//}
