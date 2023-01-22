package de.rki.demis.fhir.model.udt;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@EqualsAndHashCode(callSuper = true)
//@Table(value = "Signature")
//public class Signature extends Type implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 1587325823L;
//
//
//    @Child(name = "type", type = {org.hl7.fhir.r4.model.Coding.class},
//            order = 0, min = 1, max = Child.MAX_UNLIMITED, summary = true)
//    @Description(
//            shortDefinition = "Indication of the reason the entity signed the object(s)",
//            value = "An indication of the reason that the entity signed this document. This may be explicitly included " +
//                    "as part of the signature information and can be used when determining accountability for various " +
//                    "actions concerning the document.")
//    @CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.UDT)
//    private Set<Coding> type;
//
//    @Column("created_date")
//    @Child(name = "when", type = {org.hl7.fhir.r4.model.InstantType.class},
//            order = 1, min = 1, summary = true)
//    @Description(
//            shortDefinition = "When the signature was created",
//            value = "When the digital signature was signed.")
//    private Date when;
//
//    @Child(name = "who", type = {org.hl7.fhir.r4.model.Practitioner.class,
//            org.hl7.fhir.r4.model.PractitionerRole.class, org.hl7.fhir.r4.model.RelatedPerson.class,
//            org.hl7.fhir.r4.model.Patient.class, org.hl7.fhir.r4.model.Device.class, org.hl7.fhir.r4.model.Organization.class},
//            order = 2, min = 1, summary = true)
//    @Description(
//            shortDefinition = "Who signed",
//            value = "A reference to an application-usable description of the identity that " +
//                    "signed  (e.g. the signature used their private key).")
//    private Reference who;
//
//    private Resource whoTarget;
//
//    @Child(name = "onBehalfOf", type = {org.hl7.fhir.r4.model.Practitioner.class, org.hl7.fhir.r4.model.PractitionerRole.class,
//            org.hl7.fhir.r4.model.RelatedPerson.class, org.hl7.fhir.r4.model.Patient.class,
//            org.hl7.fhir.r4.model.Device.class, org.hl7.fhir.r4.model.Organization.class},
//            order = 3, summary = true)
//    @Description(shortDefinition = "The party represented", value = "A reference to an application-usable description of the identity that is represented by the signature.")
//    @CassandraType(type = CassandraType.Name.UDT)
//    private Reference onBehalfOf;
//
//    private Resource onBehalfOfTarget;
//
//    @Child(name = "targetFormat", type = {org.hl7.fhir.r4.model.CodeType.class},
//            order = 4)
//    @Description(
//            shortDefinition = "The technical format of the signed resources",
//            value = "A mime type that indicates the technical format of the " +
//                    "target resources signed by the signature.")
//    private CodeType targetFormat;
//
//    @Child(name = "sigFormat", type = {org.hl7.fhir.r4.model.CodeType.class},
//            order = 5)
//    @Description(
//            shortDefinition = "The technical format of the signature",
//            value = "A mime type that indicates the technical format of the signature. " +
//                    "Important mime types are application/signature+xml for X ML DigSig, " +
//                    "application/jose for JWS, and image/* for a graphical image of a signature, etc.")
//    private CodeType sigFormat;
//
//    @Child(name = "data", type = {org.hl7.fhir.r4.model.Base64BinaryType.class},
//            order = 6)
//    @Description(
//            shortDefinition = "The actual signature content (XML DigSig. JWS, picture, etc.)",
//            value = "The base64 encoding of the Signature content. When signature is not " +
//                    "recorded electronically this element would be empty.")
////    private Base64BinaryType data;
//    @CassandraType(type = CassandraType.Name.VARCHAR)
//    private byte[] data; // base64Binary: JSON string - base64 content - Regex: (\s*([0-9a-zA-Z\+\=]){4}\s*)+
//}
