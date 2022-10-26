package de.rki.demis.fhir.util.fhir_object.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IBase extends Serializable {

    boolean isEmpty();

    /**
     * Returns <code>true</code> if any comments would be returned by {@link #getFormatCommentsPre()}
     * or {@link #getFormatCommentsPost()}
     *
     * @since 1.5
     */
    boolean hasFormatComment();

    /**
     * Returns a list of comments appearing immediately before this element within the serialized
     * form of the resource. Creates the list if it does not exist, so this method will not return <code>null</code>
     *
     * @since 1.5
     */
    List<String> getFormatCommentsPre();

    /**
     * Returns a list of comments appearing immediately after this element within the serialized
     * form of the resource. Creates the list if it does not exist, so this method will not return <code>null</code>
     *
     * @since 1.5
     */
    List<String> getFormatCommentsPost();

    /**
     * Returns the FHIR type name for the given element, e.g. "Patient" or "unsignedInt"
     */
    default String fhirType() {
        return null;
    }

    /**
     * Retrieves any user supplied data in this element
     */
    Object getUserData(String theName);

    /**
     * Sets a user supplied data value in this element
     */
    void setUserData(String theName, Object theValue);

}
