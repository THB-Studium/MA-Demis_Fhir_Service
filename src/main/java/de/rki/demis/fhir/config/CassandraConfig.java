//package de.rki.demis.fhir.config;
//
//import com.datastax.oss.driver.api.core.CqlSession;
//import lombok.Getter;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.cassandra.SessionFactory;
//import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
//import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
//import org.springframework.data.cassandra.config.SchemaAction;
//import org.springframework.data.cassandra.config.SessionFactoryFactoryBean;
//import org.springframework.data.cassandra.core.CassandraOperations;
//import org.springframework.data.cassandra.core.CassandraTemplate;
//import org.springframework.data.cassandra.core.convert.CassandraConverter;
//import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
//import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
//import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
//import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
//import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
//
//import java.util.List;
//
//@Getter
//@Configuration
//@EnableCassandraRepositories(basePackages = {"de.rki.demis.fhir.repository"})
//public class CassandraConfig extends AbstractCassandraConfiguration {
//    @Value(value = "${spring.data.cassandra.schema-action}")
//    private SchemaAction schemaAction;
//    @Value(value = "${spring.data.cassandra.keyspace-name}")
//    private String keySpace;
//    @Value(value = "${spring.data.cassandra.contact-points}")
//    private String contactPoints;
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
//    public SchemaAction getSchemaAction() {
//        return schemaAction;
//    }
//
//    @NotNull
//    @Override
//    protected String getKeyspaceName() {
//        return keySpace;
//    }
//
//    @NotNull
//    @Override
//    public List<DropKeyspaceSpecification> getKeyspaceDrops() {
//        return List.of(DropKeyspaceSpecification.dropKeyspace(getKeyspaceName()));
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
//
//
//    /*
//     * Use the standard Cassandra driver API to create a com.datastax.oss.driver.api.core.CqlSession instance.
//     */
////    public @Bean CqlSession session() {
////        return CqlSession.builder().withKeyspace(getKeyspaceName()).build();
////    }
//
////    @Bean
////    public CqlSessionFactoryBean cqlSession() {
////
////        CqlSessionFactoryBean session = new CqlSessionFactoryBean();
////        session.setContactPoints(getContactPoints());
////        session.setKeyspaceName(getKeyspaceName());
////
////        return session;
////    }
////
////    @Bean
////    public SessionFactoryFactoryBean sessionFactory(CqlSession session, CassandraConverter converter) {
////
////        SessionFactoryFactoryBean sessionFactory = new SessionFactoryFactoryBean();
////        sessionFactory.setSession(session);
////        sessionFactory.setConverter(converter);
////        sessionFactory.setSchemaAction(SchemaAction.NONE);
////
////        return sessionFactory;
////    }
////
////    @Bean
////    public CassandraMappingContext mappingContext(CqlSession cqlSession) {
////
////        CassandraMappingContext mappingContext = new CassandraMappingContext();
////        mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cqlSession));
////
////        return mappingContext;
////    }
////
////    @Bean
////    public CassandraConverter converter(CassandraMappingContext mappingContext) {
////        return new MappingCassandraConverter(mappingContext);
////    }
////
////    @Bean
////    public CassandraOperations cassandraTemplate(SessionFactory sessionFactory, CassandraConverter converter) {
////        return new CassandraTemplate(sessionFactory, converter);
////    }
//
////    @Bean
////    public CassandraMigration cassandraMigration() {
////        return new CassandraMigration();
////    }
////
////    @Bean
////    public CassandraMigrationExecutor migrationExecutor(CqlSession session) {
////        return new CassandraMigrationExecutor(session, new CassandraMigrationStrategy(), cassandraMigration());
////    }
//}
//
