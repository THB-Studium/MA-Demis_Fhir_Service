package de.rki.demis.fhir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Signature extends Type implements Serializable, BaseEntity {
    @Serial
    private static final long serialVersionUID = 1587325823L;


    /***
     * shortDefinition = "Indication of the reason the entity signed the object(s)",
     *           value = "An indication of the reason that the entity signed this document. This may be explicitly included
     *                    as part of the signature information and can be used when determining accountability for various
     *                    actions concerning the document."
     ***/
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Coding> type;

    /***
     * shortDefinition = "When the signature was created",
     *           value = "When the digital signature was signed."
     ***/
    @Column(name = "created_date")
    private Date when;

    /***
     * shortDefinition = "Who signed",
     *           value = "A reference to an application-usable description of the identity that signed
     *                    (e.g. the signature used their private key)."
     ***/
    @OneToOne
    private Reference who;

    @OneToOne
    private Resource whoTarget;

    /***
     * shortDefinition = "The party represented",
     *           value = "A reference to an application-usable description of the identity that is represented by the signature."
     ***/
    @OneToOne
    private Reference onBehalfOf;

    @OneToOne
    private Resource onBehalfOfTarget;

    /***
     * shortDefinition = "The technical format of the signed resources",
     *           value = "A mime type that indicates the technical format of the target resources signed by the signature."
     ***/
    @OneToOne
    private CodeType targetFormat;

    /***
     * shortDefinition = "The technical format of the signature",
     *           value = "A mime type that indicates the technical format of the signature. Important mime types are
     *                    application/signature+xml for X ML DigSig, application/jose for JWS, and image/* for a graphical
     *                    image of a signature, etc."
     ***/
    @OneToOne
    private CodeType sigFormat;

    /***
     * shortDefinition = "The actual signature content (XML DigSig. JWS, picture, etc.)",
     *           value = "The base64 encoding of the Signature content. When signature is not recorded electronically
     *                    this element would be empty."
     ***/
    private byte[] data; // base64Binary: JSON string - base64 content - Regex: (\s*([0-9a-zA-Z\+\=]){4}\s*)+
}
