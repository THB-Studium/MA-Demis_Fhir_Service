package de.rki.demis.fhir.config;

import ca.uhn.fhir.context.FhirContext;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class FhirContextConfig {

    @Bean
    public FhirContext fhirContext() {
        return FhirContext.forR4();
    }
}
