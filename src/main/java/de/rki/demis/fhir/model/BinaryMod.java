package de.rki.demis.fhir.model;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import de.rki.demis.fhir.util.fhir_object.classes.BaseBinary;
import de.rki.demis.fhir.util.fhir_object.classes.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

import static de.rki.demis.fhir.util.constant.Constants.NOT_EMPTY_MSG;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class BinaryMod extends BaseBinary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1353224198L;


    @NotEmpty(message = "The 'contentType' " + NOT_EMPTY_MSG)
    @Child(
            name = "contentType",
            type = {org.hl7.fhir.r4.model.CodeType.class},
            order = 0, min = 1, summary = true
    )
    @Description(
            shortDefinition = "MimeType of the binary content",
            value = "MimeType of the binary content represented as a standard MimeType (BCP 13)."
    )
//    @OneToOne
//    private CodeType contentType; todo
    private String contentType;

//    @OneToOne
//    @Child(
//            name = "securityContext",
//            type = {org.hl7.fhir.r4.model.Reference.class},
//            order = 1, summary = true)
//    @Description(
//            shortDefinition = "Identifies another resource to use as proxy when enforcing access control",
//            value = "This element identifies another resource that can be used as a proxy of the security sensitivity to use when deciding and enforcing access control rules for the Binary resource. Given that the Binary resource contains very few elements that can be used to determine the sensitivity of the data and relationships to individuals, the referenced resource stands in as a proxy equivalent for this purpose. This referenced resource may be related to the Binary (e.g. Media, DocumentReference), or may be some non-related Resource purely as a security proxy. E.g. to identify that the binary resource relates to a patient, and access should only be granted to applications that have access to the patient.")
//    private Reference securityContext;
//    @OneToOne
//    private Resource securityContextTarget; todo
//    @OneToOne

    @Child(
            name = "data",
            type = {org.hl7.fhir.r4.model.Base64BinaryType.class}, order = 2)
    @Description(
            shortDefinition = "The actual content",
            value = "The actual content, base64 encoded.")
//    private Base64BinaryType data; // base64Binary: JSON string - base64 content - Regex: (\s*([0-9a-zA-Z\+\=]){4}\s*)+ // todo
    private byte[] data; // base64Binary: JSON string - base64 content - Regex: (\s*([0-9a-zA-Z\+\=]){4}\s*)+
}
