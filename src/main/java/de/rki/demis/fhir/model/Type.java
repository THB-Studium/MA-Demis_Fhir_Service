package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.classes.Element;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serial;
import java.io.Serializable;


@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class Type extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 4623040030733049991L;
}
