package de.rki.demis.fhir.util.fhir_object.interfaces;

import org.hl7.fhir.instance.model.api.IBaseEnumFactory;

public interface EnumFactory<T extends Enum<?>> extends IBaseEnumFactory<T> {

    /**
     * Read an enumeration value from the string that represents it on the XML or JSON
     *
     * @param codeString the value found in the XML or JSON
     * @return the enumeration value
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
     * Get the system URI for an enumerated value
     *
     * @param code - the enumeration value
     * @return the XML/JSON representation
     */
    String toSystem(T code);

}
