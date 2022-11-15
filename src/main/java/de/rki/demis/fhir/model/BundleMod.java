package de.rki.demis.fhir.model;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import de.rki.demis.fhir.util.fhir_object.enums.BundleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import static de.rki.demis.fhir.util.constant.Constants.NOT_NULL_MSG;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@EqualsAndHashCode(callSuper = true)
//@Table(value = "BundleMod")
//public class BundleMod extends Resource implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 1353224198L;
//
//
//    @Child(name = "identifier",
//            type = {org.hl7.fhir.r4.model.Identifier.class},
//            order = 0, summary = true)
//    @Description(shortDefinition = "Persistent identifier for the bundle", value = "A persistent " +
//            "identifier for the bundle that won't change as a bundle is copied from server to server.")
//    private Identifier identifier;
//
//    @NotNull(message = "The 'Bundle type' " + NOT_NULL_MSG)
//    @Child(name = "type",
//            type = {org.hl7.fhir.r4.model.CodeType.class},
//            order = 1, min = 1, summary = true)
//    @Description(
//            shortDefinition = "document | message | transaction | transaction-response | batch | batch-response" +
//                    "| history | searchset | collection",
//            value = "Indicates the purpose of this bundle - how it is intended to be used.")
//    private Enumeration<BundleType> type; // xs:token - JSON string - Regex: [^\s]+(\s[^\s]+)*
//
//    @Child(name = "timestamp", type = {org.hl7.fhir.r4.model.InstantType.class}, order = 2, summary = true)
//    @Description(shortDefinition = "When the bundle was assembled", value = "The date/time that the bundle was assembled - i.e. when the resources were placed in the bundle.")
//    private Date timestamp;
//
//    @Child(name = "total", type = {org.hl7.fhir.r4.model.UnsignedIntType.class}, order = 3, summary = true)
//    @Description(
//            shortDefinition = "If search, the total number of matches",
//            value = "If a set of search matches, this is the total number of entries of type 'match' across all " +
//                    "pages in the search.  It does not include search.mode = 'include' or 'outcome' entries and it " +
//                    "does not provide a count of the number of entries in the Bundle.")
//    private int total;
//
//    @Child(name = "link", order = 4,
//            max = Child.MAX_UNLIMITED, summary = true)
//    @Description(
//            shortDefinition = "Links related to this Bundle",
//            value = "A series of links that provide context to this bundle.")
//    @CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.UDT)
//    private Set<BundleLinkComponent> link;
//
//    @Child(name = "entry", order = 5,
//            max = Child.MAX_UNLIMITED, summary = true)
//    @Description(
//            shortDefinition = "Entry in the bundle - will have a resource or information",
//            value = "An entry in a bundle resource - will either contain a resource or information " +
//                    "about a resource (transactions and history only).")
//    @CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.UDT)
//    private Set<BundleEntryComponent> entry;
//
//    @Child(name = "signature", type = {org.hl7.fhir.r4.model.Signature.class},
//            order = 6, summary = true)
//    @Description(
//            shortDefinition = "Digital Signature",
//            value = "Digital Signature - base64 encoded. XML-DSig or a JWT.")
//    private Signature signature;
//}
