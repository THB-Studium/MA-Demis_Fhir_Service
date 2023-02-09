package de.rki.demis.fhir.model.table;

import de.rki.demis.fhir.model.udt.Reference;
import de.rki.demis.fhir.util.fhir_object.classes.BaseBinary;
import de.rki.demis.fhir.util.fhir_object.classes.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table
public class BinaryMod extends BaseBinary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1353224198L;


    /***
     * shortDefinition = "MimeType of the binary content",
     *           value = "MimeType of the binary content represented as a standard MimeType (BCP 13)."
     ***/
////    private CodeType contentType; todo
    private String contentType;

    /***
     * shortDefinition = "Identifies another resource to use as proxy when enforcing access control",
     *           value = "This element identifies another resource that can be used as a proxy of the security
     *                   sensitivity to use when deciding and enforcing access control rules for the Binary resource.
     *                   Given that the Binary resource contains very few elements that can be used to determine the
     *                   sensitivity of the data and relationships to individuals, the referenced resource stands in as
     *                   a proxy equivalent for this purpose. This referenced resource may be related to the Binary
     *                   (e.g. Media, DocumentReference), or may be some non-related Resource purely as a security
     *                   proxy. E.g. to identify that the binary resource relates to a patient, and access should only
     *                   be granted to applications that have access to the patient."
     ***/
    private Reference securityContext;

    @CassandraType(type = CassandraType.Name.UDT, userTypeName = "resource_udt")
    private Resource securityContextTarget;

    /***
     * shortDefinition = "The actual content",
     *           value = "The actual content, base64 encoded."
     ***/
//    private Base64BinaryType data; // base64Binary: JSON string - base64 content - Regex: (\s*([0-9a-zA-Z\+\=]){4}\s*)+ // todo
    @CassandraType(type = CassandraType.Name.VARCHAR)
    private byte[] data; // base64Binary: JSON string - base64 content - Regex: (\s*([0-9a-zA-Z\+\=]){4}\s*)+
}
