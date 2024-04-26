package de.rki.demis.fhir.util.fhir_object.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
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

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> formatCommentsPre;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> formatCommentsPost;

    public boolean isEmpty() {
        return formatCommentsPre.isEmpty() && formatCommentsPost.isEmpty();
    }
}
