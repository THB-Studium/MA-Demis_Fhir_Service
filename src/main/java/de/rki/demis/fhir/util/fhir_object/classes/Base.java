package de.rki.demis.fhir.util.fhir_object.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@MappedSuperclass
public class Base implements Serializable {
    @Serial
    private static final long serialVersionUID = -1452741316L;

    //    @ElementCollection
//    private Map<String, Object> userData; // todo: find a way to persist 'Object' type with JPA (@Convert(converter = ObjectConverter.class))
    @ElementCollection
    private Set<String> formatCommentsPre;
    @ElementCollection
    private Set<String> formatCommentsPost;

    public boolean isEmpty() {
        return true;
    }
}
