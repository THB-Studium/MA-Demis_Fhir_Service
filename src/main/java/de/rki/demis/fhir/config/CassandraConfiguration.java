//package de.rki.demis.fhir.config;
//
//import lombok.Getter;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
//import org.springframework.data.cassandra.config.SchemaAction;
//import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
//import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@Getter
//@EntityScan(basePackages = {"de.rki.demis.fhir.model"})
//@EnableReactiveCassandraRepositories(basePackages = {"de.rki.demis.fhir.repository"})
//public class CassandraConfiguration extends AbstractCassandraConfiguration {
//
//    @Value(value = "${spring.data.cassandra.keyspace-name}")
//    private String keySpace;
//
//    @Value(value = "${spring.data.cassandra.contact-points}")
//    private String contactPoints;
//
////    @Value(value = "${spring.data.cassandra.contact-points}")
////    private String domain;
//
//    @Value(value = "${spring.data.cassandra.schema-action}")
//    private SchemaAction schemaAction;
//
//    @Value(value = "${spring.data.cassandra.port}")
//    private int port;
//
//
//    /*
//     * Provide a keyspace name to the configuration.
//     */
//
//    @NotNull
//    @Override
//    public List<DropKeyspaceSpecification> getKeyspaceDrops() {
//        return List.of(DropKeyspaceSpecification.dropKeyspace(getKeyspaceName()));
//    }
//
//    @NotNull
//    @Override
//    public String getKeyspaceName() {
//        return keySpace;
//    }
//
//    @NotNull
//    @Override
//    public SchemaAction getSchemaAction() {
//        return schemaAction;
//    }
//
//    @NotNull
//    @Override
//    public String getContactPoints() {
//        return contactPoints;
//    }
//
//    @Override
//    public int getPort() {
//        return port;
//    }
//}
