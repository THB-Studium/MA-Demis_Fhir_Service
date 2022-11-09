package de.rki.demis.fhir.util.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import de.rki.demis.fhir.exception.ParsingException;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Binary;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Parameters;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static de.rki.demis.fhir.util.constant.Constants.UNSUPPORTED_RESOURCE_BUNDLE;
import static org.springframework.http.MediaType.APPLICATION_XML;

@Service
public class FhirParserService {

    private final IParser xmlParser;
    private final IParser jsonParser;

    public FhirParserService(FhirContext fhirContext) {
        this.xmlParser = fhirContext.newXmlParser();
        this.jsonParser = fhirContext.newJsonParser();
    }

    public Bundle parseBundle(String content, MediaType mediaType) throws ParsingException {
        IBaseResource resource = getParser(mediaType).parseResource(content);

        if (resource instanceof Parameters parameters) {
            return (Bundle) parameters.getParameterFirstRep().getResource();
        } else if (resource instanceof Bundle bundle) {
            return bundle;
        }
        throw new ParsingException(UNSUPPORTED_RESOURCE_BUNDLE);
    }

    public Binary parseBinary(String content, MediaType mediaType) throws ParsingException {
        IBaseResource resource = getParser(mediaType).parseResource(content);

        if (resource instanceof Parameters parameters) {
            return (Binary) parameters.getParameterFirstRep().getResource();
        } else if (resource instanceof Binary binary) {
            return binary;
        }

        throw new ParsingException(UNSUPPORTED_RESOURCE_BUNDLE);
    }

    public IBaseResource parseFromJson(String content) {
        return jsonParser.parseResource(content);
    }

    public String encode(IBaseResource resource, MediaType mediaType) {
        return getParser(mediaType).encodeResourceToString(resource);
    }

    private IParser getParser(MediaType mediaType) {
        Objects.requireNonNull(mediaType, "require NonNull mediaType");
        return mediaType.getSubtype().equals(APPLICATION_XML.getSubtype()) ? xmlParser : jsonParser;
    }
}

