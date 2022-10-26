package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.classes.Resource;
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
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import static de.rki.demis.fhir.util.constant.Constants.NOT_EMPTY_MSG;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class BundleMod extends Resource implements Serializable {
    @Serial
    private static final long serialVersionUID = 1353224198L;

    @OneToOne
    private Identifier identifier;
    @NotEmpty(message = "The 'Bundle type' " + NOT_EMPTY_MSG)
    private String type; // xs:token - JSON string - Regex: [^\s]+(\s[^\s]+)*
    private Date timestamp;
    private int total;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<BackboneElement> link;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<BackboneElement> response;
}
