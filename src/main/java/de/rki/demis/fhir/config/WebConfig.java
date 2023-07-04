package de.rki.demis.fhir.config;

import de.rki.demis.fhir.converter.Binary2BinaryModConverter;
import de.rki.demis.fhir.converter.Bundle2BundleModConverter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(@NotNull FormatterRegistry registry) {
        registry.addConverter(new Binary2BinaryModConverter());
        registry.addConverter(new Bundle2BundleModConverter());
    }
}
