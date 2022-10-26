package de.rki.demis.fhir.util.fhir_object.interfaces;

import java.io.Serializable;

public interface IBaseEnumFactory<T extends Enum<?>> extends Serializable {

    /**
     * Read an enumeration value from the string that represents it on the XML or JSON
     *
     * @param codeString the value found in the XML or JSON
     * @return the enumeration value
     * @throws IllegalArgumentException is the value is not known
     */
    T fromCode(String codeString) throws IllegalArgumentException;

    /**
     * Get the XML/JSON representation for an enumerated value
     *
     * @param code - the enumeration value
     * @return the XML/JSON representation
     */
    String toCode(T code);

    /**
     * Get the system for a given enum value
     */
    default String toSystem(T theValue) {
        return null;
    }

}
