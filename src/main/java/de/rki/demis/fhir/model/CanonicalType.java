package de.rki.demis.fhir.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class CanonicalType extends UriType implements Serializable, BaseEntity {
    @Serial
    private static final long serialVersionUID = 4L;
}
