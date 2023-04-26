package de.rki.demis.fhir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaAuditing
@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = {"de.rki.demis.fhir.model"})
@EnableJpaRepositories(basePackages = {"de.rki.demis.fhir.repository"})
public class FhirServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FhirServerApplication.class, args);
    }

}
