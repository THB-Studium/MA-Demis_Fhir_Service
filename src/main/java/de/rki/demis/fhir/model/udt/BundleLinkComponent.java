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

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

import static de.rki.demis.fhir.util.constant.Constants.NOT_NULL_MSG;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
//@ToString
//@Table(value = "BundleLinkComponent")
//public class BundleLinkComponent extends BackboneElement implements Serializable {
//    @Serial
//    private static final long serialVersionUID = -1010386066L;
//
//
//    @NotNull(message = "The 'relation' " + NOT_NULL_MSG)
//    @Child(name = "relation", type = {org.hl7.fhir.r4.model.StringType.class},
//            order = 1, min = 1, summary = true)
//    @Description(
//            shortDefinition = "See http://www.iana.org/assignments/link-relations/link-relations.xhtml#link-relations-1",
//            value = "A name which details the functional use for this link - see " +
//                    "[http://www.iana.org/assignments/link-relations/link-relations.xhtml#link-relations-1]" +
//                    "(http://www.iana.org/assignments/link-relations/link-relations.xhtml#link-relations-1).")
//    private String relation;
//
//    @NotNull(message = "The 'url' " + NOT_NULL_MSG)
//    @Child(name = "url", type = {org.hl7.fhir.r4.model.UriType.class},
//            order = 2, min = 1, summary = true)
//    @Description(
//            shortDefinition = "Reference details for the link",
//            value = "The reference details for the link.")
//    @CassandraType(type = CassandraType.Name.UDT)
//    private UriType url;
//
//}
