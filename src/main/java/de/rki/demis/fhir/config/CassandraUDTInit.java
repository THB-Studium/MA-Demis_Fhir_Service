package de.rki.demis.fhir.config;

import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static de.rki.demis.fhir.util.constant.Constants.DELETE_TABLE;
import static de.rki.demis.fhir.util.constant.Constants.DELETE_UDT;

@Component
public class CassandraUDTInit {
    private final AppSettings settings;
    private final CassandraTemplate cassandraTemplate;

    @Autowired
    public CassandraUDTInit(AppSettings settings, CassandraTemplate cassandraTemplate) {
        this.settings = settings;
        this.cassandraTemplate = cassandraTemplate;
    }

    @PostConstruct
    public void init() {
        if (settings.isInitData()) {
            deleteTables();
            deleteUDTs();
        }
    }

    private void deleteTables() {
        cassandraTemplate.execute(SimpleStatement.newInstance(DELETE_TABLE + ".BinaryMod;"));
        cassandraTemplate.execute(SimpleStatement.newInstance(DELETE_TABLE + ".BundleMod;"));
    }

    private void deleteUDTs() {
        cassandraTemplate.execute(SimpleStatement.newInstance(DELETE_UDT + ".MetaUDT;"));
        cassandraTemplate.execute(SimpleStatement.newInstance(DELETE_UDT + ".CanonicalTypeUDT;"));
        cassandraTemplate.execute(SimpleStatement.newInstance(DELETE_UDT + ".IdentifierUDT;"));
        cassandraTemplate.execute(SimpleStatement.newInstance(DELETE_UDT + ".CodeableConceptUDT;"));
        cassandraTemplate.execute(SimpleStatement.newInstance(DELETE_UDT + ".CodingUDT;"));
        cassandraTemplate.execute(SimpleStatement.newInstance(DELETE_UDT + ".CodeTypeUDT;"));
        cassandraTemplate.execute(SimpleStatement.newInstance(DELETE_UDT + ".EnumerationUDT;"));
        cassandraTemplate.execute(SimpleStatement.newInstance(DELETE_UDT + ".ReferenceUDT;"));
        cassandraTemplate.execute(SimpleStatement.newInstance(DELETE_UDT + ".UriTypeUDT;"));
    }

}
