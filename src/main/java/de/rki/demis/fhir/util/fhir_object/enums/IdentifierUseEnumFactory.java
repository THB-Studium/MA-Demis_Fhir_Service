package de.rki.demis.fhir.util.fhir_object.enums;

import java.io.Serializable;


public class IdentifierUseEnumFactory implements Serializable {
    public IdentifierUse fromCode(String codeString) throws IllegalArgumentException {
        if (codeString == null || codeString.isEmpty())
            return null;
        if ("usual".equals(codeString))
            return IdentifierUse.USUAL;
        if ("official".equals(codeString))
            return IdentifierUse.OFFICIAL;
        if ("temp".equals(codeString))
            return IdentifierUse.TEMP;
        if ("secondary".equals(codeString))
            return IdentifierUse.SECONDARY;
        if ("old".equals(codeString))
            return IdentifierUse.OLD;
        throw new IllegalArgumentException("::: Unknown IdentifierUse code '" + codeString + "' :::");
    }

    public String toCode(IdentifierUse code) {
        if (code == IdentifierUse.USUAL)
            return "usual";
        if (code == IdentifierUse.OFFICIAL)
            return "official";
        if (code == IdentifierUse.TEMP)
            return "temp";
        if (code == IdentifierUse.SECONDARY)
            return "secondary";
        if (code == IdentifierUse.OLD)
            return "old";
        return "?";
    }

    public String toSystem(IdentifierUse code) {
        return code.getSystem();
    }
}
