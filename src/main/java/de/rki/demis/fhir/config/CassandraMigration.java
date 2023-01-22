//package de.rki.demis.fhir.config;
//
//import org.springframework.data.cassandra.core.cql.CqlOperations;
//
//
//public class CassandraMigration extends AbstractCassandraMigration {
//
//    @Override
//    public void doMigrate(CqlOperations cqlOperations) {
//        // tables:
//        cqlOperations.execute("DROP TABLE IF EXISTS BinaryMod;");
//        cqlOperations.execute("DROP TABLE IF EXISTS BundleMod;");
//
//        // User Defined Types (UDT):
//        cqlOperations.execute("DROP TYPE IF EXISTS MetaUDT;");
//        cqlOperations.execute("DROP TYPE IF EXISTS CanonicalTypeUDT;");
//        cqlOperations.execute("DROP TYPE IF EXISTS IdentifierUDT;");
//        cqlOperations.execute("DROP TYPE IF EXISTS CodeableConceptUDT;");
//        cqlOperations.execute("DROP TYPE IF EXISTS CodingUDT;");
//        cqlOperations.execute("DROP TYPE IF EXISTS CodeTypeUDT;");
//        cqlOperations.execute("DROP TYPE IF EXISTS EnumerationUDT;");
//        cqlOperations.execute("DROP TYPE IF EXISTS ReferenceUDT;");
//        cqlOperations.execute("DROP TYPE IF EXISTS UriTypeUDT;");
//    }
//}
//
