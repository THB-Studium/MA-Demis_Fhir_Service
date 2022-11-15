package de.rki.demis.fhir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@EqualsAndHashCode(callSuper = true)
//@Table(value = "Base64BinaryType")
//public class Base64BinaryType extends PrimitiveType<byte[]> implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 3L;
//
//    @CassandraType(type = CassandraType.Name.VARCHAR)
//    private byte[] myValue;
//}
