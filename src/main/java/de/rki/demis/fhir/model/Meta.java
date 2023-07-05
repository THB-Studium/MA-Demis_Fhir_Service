package de.rki.demis.fhir.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.rki.demis.fhir.util.constant.DateFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Meta extends Type implements Serializable, BaseEntity {
    @Serial
    private static final long serialVersionUID = -1386695622L;


    /***
     * shortDefinition = "Version specific identifier",
     *           value = "The version specific identifier, as it appears in the version portion of the URL.
     *                    This value changes when the resource is created, updated, or deleted."
     ***/
    private String versionId;

    /***
     * shortDefinition = "When the resource version last changed",
     *           value = "When the resource last changed - e.g. when the version changed."
     ***/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.DATE_TIME_FORMAT)
    private Date lastUpdated;

    /***
     * shortDefinition = "Identifies where the resource comes from",
     *           value = "A uri that identifies the source system of the resource. This provides a minimal amount of
     *                    [[[Provenance]]] information that can be used to track or differentiate the source of information
     *                    in the resource. The source may identify another FHIR server, document, message, database, etc."
     ***/
    @OneToOne
    private UriType source; // xs:anyURI - JSON string - a URI - Regex: \S*

    /***
     * shortDefinition = "Profiles this resource claims to conform to",
     *           value = "A list of profiles (references to [[[StructureDefinition]]] resources) that this resource claims
     *                    to conform to. The URL is a reference to [[[StructureDefinition.url]]]."
     ***/
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<CanonicalType> profile; // JSON string - a canonical URL

    /***
     * shortDefinition = "Security Labels applied to this resource",
     *           value = "Security labels applied to this resource. These tags connect specific resources to the
     *                    overall security policy and infrastructure."
     ***/
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Coding> security;

    /***
     * shortDefinition = "Tags applied to this resource",
     *           value = "Tags applied to this resource. Tags are intended to be used to identify and relate resources to
     *                    process and workflow, and applications are not required to consider the tags when interpreting
     *                    the meaning of a resource."
     ***/
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Coding> tag;
}
