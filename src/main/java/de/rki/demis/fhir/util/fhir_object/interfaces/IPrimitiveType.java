package de.rki.demis.fhir.util.fhir_object.interfaces;

import javax.annotation.Nullable;

public interface IPrimitiveType<T> extends IBaseDatatype {

    /**
     * If the supplied argument is non-null, returns the results of {@link #getValue()}. If the supplied argument is null, returns null.
     */
    @Nullable
    static <T> T toValueOrNull(@Nullable IPrimitiveType<T> thePrimitiveType) {
        return thePrimitiveType != null ? thePrimitiveType.getValue() : null;
    }

    String getValueAsString();

    void setValueAsString(String theValue) throws IllegalArgumentException;

    T getValue();

    IPrimitiveType<T> setValue(T theValue) throws IllegalArgumentException;

    boolean hasValue();

}
