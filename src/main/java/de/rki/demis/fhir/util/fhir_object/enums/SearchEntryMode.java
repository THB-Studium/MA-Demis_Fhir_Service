package de.rki.demis.fhir.util.fhir_object.enums;

import org.hl7.fhir.exceptions.FHIRException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import static org.hl7.fhir.r4.model.Configuration.isAcceptInvalidEnums;

public enum SearchEntryMode {
    /**
     * This resource matched the search specification.
     */
    MATCH,

    /**
     * This resource is returned because it is referred to from another resource in the search set.
     */
    INCLUDE,

    /**
     * An OperationOutcome that provides additional information about the processing of a search.
     */
    OUTCOME,

    /**
     * added to help the parsers with the generic types
     */
    NULL;


    public static SearchEntryMode fromCode(String codeString) throws FHIRException {
        if (codeString == null || "".equals(codeString))
            return null;
        if ("match".equals(codeString))
            return MATCH;
        if ("include".equals(codeString))
            return INCLUDE;
        if ("outcome".equals(codeString))
            return OUTCOME;
        if (isAcceptInvalidEnums())
            return null;
        else
            throw new FHIRException(String.format("::: Unknown SearchEntryMode code '%s' :::", codeString));
    }

    @Nullable
    @Contract(pure = true)
    public String toCode() {
        return switch (this) {
            case MATCH -> "match";
            case INCLUDE -> "include";
            case OUTCOME -> "outcome";
            case NULL -> null;
            default -> "?";
        };
    }

    @Nullable
    @Contract(pure = true)
    public String getSystem() {
        return switch (this) {
            case MATCH, OUTCOME, INCLUDE -> "http://hl7.org/fhir/search-entry-mode";
            case NULL -> null;
            default -> "?";
        };
    }

    @Nullable
    @Contract(pure = true)
    public String getDefinition() {
        return switch (this) {
            case MATCH -> "This resource matched the search specification.";
            case INCLUDE ->
                    "This resource is returned because it is referred to from another resource in the search set.";
            case OUTCOME ->
                    "An OperationOutcome that provides additional information about the processing of a search.";
            case NULL -> null;
            default -> "?";
        };
    }

    @Nullable
    @Contract(pure = true)
    public String getDisplay() {
        return switch (this) {
            case MATCH -> "Match";
            case INCLUDE -> "Include";
            case OUTCOME -> "Outcome";
            case NULL -> null;
            default -> "?";
        };
    }
}

