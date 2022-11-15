package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.classes.BaseResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(value = "Resource")
public class Resource extends BaseResource implements Serializable {
    @Serial
    private static final long serialVersionUID = -559462759L;

    /***
     * shortDefinition = "Logical id of this artifact",
     *           value = "The logical id of the resource, as used in the URL for the resource.
     *                    Once assigned, this value never changes."
     ***/
    @PrimaryKey
    private UUID id;

    private String resourceType;

    /***
     * shortDefinition = "Metadata about the resource",
     *           value = "The metadata about the resource. This is content that is maintained by the infrastructure.
     *                    Changes to the content might not always be associated with version changes to the resource."
     ***/
    @CassandraType(type = CassandraType.Name.UUID)
    private Meta meta;

    /***
     * shortDefinition = "A set of rules under which this content was created",
     *           value = "A reference to a set of rules that were followed when the resource was constructed, and which
     *                    must be understood when processing the content. Often, this is a reference to an implementation
     *                    guide that defines the special rules along with other profiles etc."
     ***/
    @CassandraType(type = CassandraType.Name.UUID)
    private UriType implicitRules; // xs:anyURI - 	A JSON string - a URI - Regex: \S*

    /***
     * shortDefinition = "Language of the resource content",
     *           value = "The base language in which the resource is written."
     ***/
    @CassandraType(type = CassandraType.Name.UUID)
    private CodeType language; // xs:token - JSON string - Regex: [^\s]+(\s[^\s]+)*
}
