package de.rki.demis.fhir.config;

import de.rki.demis.fhir.util.converter.Binary2BinaryModConverter;
import de.rki.demis.fhir.util.converter.Bundle2BundleModConverter;
import de.rki.demis.fhir.util.converter.EnumerationToEnumBundleTypeConverter;
import de.rki.demis.fhir.util.converter.EnumerationToEnumHTTPVerbConverter;
import de.rki.demis.fhir.util.converter.EnumerationToEnumIdentifierUseConverter;
import de.rki.demis.fhir.util.converter.EnumerationToEnumSearchEntryModeConverter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(@NotNull FormatterRegistry registry) {
        registry.addConverter(new Binary2BinaryModConverter());
        registry.addConverter(new Bundle2BundleModConverter());
        registry.addConverter(new EnumerationToEnumBundleTypeConverter());
        registry.addConverter(new EnumerationToEnumHTTPVerbConverter());
        registry.addConverter(new EnumerationToEnumSearchEntryModeConverter());
        registry.addConverter(new EnumerationToEnumIdentifierUseConverter());
//        registry.addConverter(new UriType2UdtConverter());
//        registry.addConverter(new Reference2UdtConverter());
//        registry.addConverter(new CodeType2UdtConverter());
//        registry.addConverter(new ResourceToUdtValueConverter());
    }

}
