package de.rki.demis.fhir.model;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Meta extends Type implements Serializable {
    @Serial
    private static final long serialVersionUID = -1386695622L;

    @Child(name = "versionId", type = {IdType.class}, order = 0, summary = true)
    @Description(
            shortDefinition = "Version specific identifier",
            value = "The version specific identifier, as it appears in the version portion of the URL. This value changes when the resource is created, updated, or deleted.")
    private String versionId;
    @Child(name = "lastUpdated", type = {InstantType.class}, order = 1, summary = true)
    @Description(
            shortDefinition = "When the resource version last changed",
            value = "When the resource last changed - e.g. when the version changed.")
    private Date lastUpdated;
    @OneToOne
    @Child(name = "source", type = {org.hl7.fhir.r4.model.UriType.class},
            order = 2, summary = true)
    @Description(
            shortDefinition = "Identifies where the resource comes from",
            value = "A uri that identifies the source system of the resource. This provides a minimal amount of [[[Provenance]]] information that can be used to track or differentiate the source of information in the resource. The source may identify another FHIR server, document, message, database, etc.")
    private UriType source; // xs:anyURI - JSON string - a URI - Regex: \S*
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Child(name = "profile", type = {org.hl7.fhir.r4.model.CanonicalType.class},
            order = 3, max = -1, summary = true)
    @Description(
            shortDefinition = "Profiles this resource claims to conform to",
            value = "A list of profiles (references to [[[StructureDefinition]]] resources) that this resource claims to conform to. The URL is a reference to [[[StructureDefinition.url]]].")
    private Set<CanonicalType> profile; // JSON string - a canonical URL
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Child(name = "security", type = {org.hl7.fhir.r4.model.Coding.class},
            order = 4, max = -1, summary = true)
    @Description(
            shortDefinition = "Security Labels applied to this resource",
            value = "Security labels applied to this resource. These tags connect specific resources to the overall security policy and infrastructure.")
    private Set<Coding> security;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Child(name = "tag", type = {org.hl7.fhir.r4.model.Coding.class},
            order = 5, max = -1, summary = true)
    @Description(
            shortDefinition = "Tags applied to this resource",
            value = "Tags applied to this resource. Tags are intended to be used to identify and relate resources to process and workflow, and applications are not required to consider the tags when interpreting the meaning of a resource.")
    private Set<Coding> tag;
}
