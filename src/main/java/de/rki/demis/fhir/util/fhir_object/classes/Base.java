package de.rki.demis.fhir.util.fhir_object.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.CassandraType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Base implements Serializable {
    @Serial
    private static final long serialVersionUID = -1452741316L;

    //    private Map<String, Object> userData; todo: find a way to persist 'Object'/'Generic' types with JPA (@Convert(converter = ObjectConverter.class))
    @CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.TEXT)
    private Set<String> formatCommentsPre;

    @CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.TEXT)
    private Set<String> formatCommentsPost;

    public boolean isEmpty() {
        return true;
    }
}
