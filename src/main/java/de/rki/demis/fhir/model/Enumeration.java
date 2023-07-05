package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.classes.PrimitiveType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;


@Data
//@AllArgsConstructor // todo: should uncommitted after resolving of jpa-generic-issue
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Enumeration<T extends Enum<?>> extends PrimitiveType<T> implements Serializable, BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

//    private EnumFactory<T> myEnumFactory; // todo: jpa-issue with generic attribute
}
