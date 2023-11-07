package de.rki.demis.fhir.util.fhir_object.enums;

import de.rki.demis.fhir.util.fhir_object.classes.Configuration;
import org.hl7.fhir.exceptions.FHIRException;

public enum HTTPVerb {
    /**
     * HTTP GET Command.
     */
    GET,

    /**
     * HTTP HEAD Command.
     */
    HEAD,

    /**
     * HTTP POST Command.
     */
    POST,

    /**
     * HTTP PUT Command.
     */
    PUT,

    /**
     * HTTP DELETE Command.
     */
    DELETE,

    /**
     * HTTP PATCH Command.
     */
    PATCH,

    /**
     * added to help the parsers with the generic types
     */
    NULL;


    public static HTTPVerb fromCode(String codeString) throws FHIRException {
        if (codeString == null || codeString.isEmpty())
            return null;
        if ("GET".equals(codeString))
            return GET;
        if ("HEAD".equals(codeString))
            return HEAD;
        if ("POST".equals(codeString))
            return POST;
        if ("PUT".equals(codeString))
            return PUT;
        if ("DELETE".equals(codeString))
            return DELETE;
        if ("PATCH".equals(codeString))
            return PATCH;
        if (Configuration.isAcceptInvalidEnums())
            return null;
        else
            throw new FHIRException("::: Unknown HTTPVerb code '" + codeString + "' :::");
    }

    public String toCode() {
        return switch (this) {
            case GET -> "GET";
            case HEAD -> "HEAD";
            case POST -> "POST";
            case PUT -> "PUT";
            case DELETE -> "DELETE";
            case PATCH -> "PATCH";
            case NULL -> null;
        };
    }

    public String getSystem() {
        return switch (this) {
            case GET, PATCH, DELETE, PUT, POST, HEAD -> "http://hl7.org/fhir/http-verb";
            case NULL -> null;
        };
    }

    public String getDefinition() {
        return switch (this) {
            case GET -> "HTTP GET Command.";
            case HEAD -> "HTTP HEAD Command.";
            case POST -> "HTTP POST Command.";
            case PUT -> "HTTP PUT Command.";
            case DELETE -> "HTTP DELETE Command.";
            case PATCH -> "HTTP PATCH Command.";
            case NULL -> null;
        };
    }

    public String getDisplay() {
        return switch (this) {
            case GET -> "GET";
            case HEAD -> "HEAD";
            case POST -> "POST";
            case PUT -> "PUT";
            case DELETE -> "DELETE";
            case PATCH -> "PATCH";
            case NULL -> null;
        };
    }
}

