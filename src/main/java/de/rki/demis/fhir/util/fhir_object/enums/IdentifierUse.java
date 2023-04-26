package de.rki.demis.fhir.util.fhir_object.enums;

import de.rki.demis.fhir.util.fhir_object.classes.Configuration;
import org.hl7.fhir.exceptions.FHIRException;


public enum IdentifierUse {
    /**
     * The identifier recommended for display and use in real-world interactions.
     */
    USUAL,

    /**
     * The identifier considered to be most trusted for the identification of this item. Sometimes also known as "primary" and "main". The determination of "official" is subjective and implementation guides often provide additional guidelines for use.
     */
    OFFICIAL,

    /**
     * A temporary identifier.
     */
    TEMP,

    /**
     * An identifier that was assigned in secondary use - it serves to identify the object in a relative context, but cannot be consistently assigned to the same object again in a different context.
     */
    SECONDARY,

    /**
     * The identifier id no longer considered valid, but may be relevant for search purposes.  E.g. Changes to identifier schemes, account merges, etc.
     */
    OLD,

    /**
     * added to help the parsers with the generic types
     */
    NULL;


    public static IdentifierUse fromCode(String codeString) throws FHIRException {
        if (codeString == null || codeString.isEmpty())
            return null;
        if ("usual".equals(codeString))
            return USUAL;
        if ("official".equals(codeString))
            return OFFICIAL;
        if ("temp".equals(codeString))
            return TEMP;
        if ("secondary".equals(codeString))
            return SECONDARY;
        if ("old".equals(codeString))
            return OLD;
        if (Configuration.isAcceptInvalidEnums())
            return null;
        else
            throw new FHIRException("::: Unknown IdentifierUse code '" + codeString + "' :::");
    }

    public String toCode() {
        return switch (this) {
            case USUAL -> "usual";
            case OFFICIAL -> "official";
            case TEMP -> "temp";
            case SECONDARY -> "secondary";
            case OLD -> "old";
            case NULL -> null;
        };
    }

    public String getSystem() {
        return switch (this) {
            case USUAL, OFFICIAL, TEMP, SECONDARY, OLD -> "http://hl7.org/fhir/identifier-use";
            case NULL -> null;
        };
    }

    public String getDefinition() {
        return switch (this) {
            case USUAL -> "The identifier recommended for display and use in real-world interactions.";
            case OFFICIAL ->
                    "The identifier considered to be most trusted for the identification of this item. Sometimes also known as \"primary\" and \"main\". The determination of \"official\" is subjective and implementation guides often provide additional guidelines for use.";
            case TEMP -> "A temporary identifier.";
            case SECONDARY ->
                    "An identifier that was assigned in secondary use - it serves to identify the object in a relative context, but cannot be consistently assigned to the same object again in a different context.";
            case OLD ->
                    "The identifier id no longer considered valid, but may be relevant for search purposes.  E.g. Changes to identifier schemes, account merges, etc.";
            case NULL -> null;
        };
    }

    public String getDisplay() {
        return switch (this) {
            case USUAL -> "Usual";
            case OFFICIAL -> "Official";
            case TEMP -> "Temp";
            case SECONDARY -> "Secondary";
            case OLD -> "Old";
            case NULL -> null;
        };
    }
}
