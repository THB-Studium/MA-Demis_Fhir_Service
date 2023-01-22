package de.rki.demis.fhir.util.fhir_object.classes;

import de.rki.demis.fhir.model.Extension;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Element extends Base implements Serializable {
    @Serial
    private static final long serialVersionUID = -1452745816L;


    /***
     * shortDefinition = "Unique id for inter-element referencing",
     *           value = "Unique id for the element within a resource (for internal references). This may be any string
     *                    value that does not contain spaces."
     ***/
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    private UUID id;

    /***
     * shortDefinition = "Additional content defined by implementations",
     *           value = "May be used to represent additional information that is not part of the basic definition of the element.
     *                    To make the use of extensions safe and manageable, there is a strict set of governance  applied
     *                    to the definition and use of extensions. Though any implementer can define an extension, there
     *                    is a set of requirements that SHALL be met as part of the definition of the extension."
     ***/
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Extension> extension;

    private boolean disallowExtensions;
}
