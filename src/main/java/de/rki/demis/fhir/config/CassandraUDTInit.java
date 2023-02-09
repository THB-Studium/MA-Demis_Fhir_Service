package de.rki.demis.fhir.config;

import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static de.rki.demis.fhir.util.constant.Constants.CREATE_UDT;
import static de.rki.demis.fhir.util.constant.Constants.DELETE_TABLE;
import static de.rki.demis.fhir.util.constant.Constants.DELETE_UDT;

@Component
public class CassandraUDTInit {
    private final AppSettings settings;
    private final CassandraTemplate cassandraTemplate;

    private final String[] _udts = {
            "MetaUDT", "CanonicalTypeUDT", "IdentifierUDT", "CodeableConceptUDT", "CodingUDT",
            "CodeTypeUDT", "EnumerationUDT", "ReferenceUDT", "UriTypeUDT"
    };

    private final String[] _tables = {
            "BinaryMod", "BundleMod"
    };

    @Autowired
    public CassandraUDTInit(AppSettings settings, CassandraTemplate cassandraTemplate) {
        this.settings = settings;
        this.cassandraTemplate = cassandraTemplate;
    }

    @PostConstruct
    public void init() {
        if (settings.isClearDB()) {
            deleteTables();
            deleteUDTs();
        }

        if (settings.isInitUDT()) {
            createUDTs();
        }
    }

    private void deleteTables() {
        for (String item : _tables) {
            cqlQueryOperation(DELETE_TABLE, item);
        }
    }

    private void deleteUDTs() {
        for (String item : _udts) {
            cqlQueryOperation(DELETE_UDT, item);
        }
    }

    private void createUDTs() {
        for (String item : _udts) {
            cqlQueryOperation(CREATE_UDT, item);
        }
    }

    private void cqlQueryOperation(String cqlOperation, String cqlInstance) {
        cassandraTemplate.execute(SimpleStatement.newInstance(cqlOperation + "." + cqlInstance));
    }

}
