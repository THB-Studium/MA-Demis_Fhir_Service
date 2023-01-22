package de.rki.demis.fhir.util.fhir_object.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.CassandraType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Base implements Serializable {
    @Serial
    private static final long serialVersionUID = -1452741316L;

    @CassandraType(type = CassandraType.Name.MAP, typeArguments = {CassandraType.Name.TEXT, CassandraType.Name.TEXT})
    private Map<String, Object> userData;
    private Set<String> formatCommentsPre;
    private Set<String> formatCommentsPost;

    public boolean isEmpty() {
        return true;
    }
}
