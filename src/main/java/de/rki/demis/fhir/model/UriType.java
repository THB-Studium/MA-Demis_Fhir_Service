package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.classes.PrimitiveType;
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
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UriType extends PrimitiveType<String> implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
}
