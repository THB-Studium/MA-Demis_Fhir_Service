package de.rki.demis.fhir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;


@SpringBootApplication
@EntityScan(basePackages = {"de.rki.demis.fhir.model"})
@EnableReactiveCassandraRepositories(basePackages = {"de.rki.demis.fhir.repository"})
public class FhirServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FhirServerApplication.class, args);
    }

}
