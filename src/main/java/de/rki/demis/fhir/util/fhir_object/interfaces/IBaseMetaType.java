package de.rki.demis.fhir.util.fhir_object.interfaces;

import java.util.Date;
import java.util.Set;

public interface IBaseMetaType extends ICompositeType {

    IBaseMetaType addProfile(String theProfile);

    IBaseCoding addSecurity();

    IBaseCoding addTag();

    Date getLastUpdated();

    IBaseMetaType setLastUpdated(Date theHeaderDateValue);

    Set<? extends IPrimitiveType<String>> getProfile();

    Set<? extends IBaseCoding> getSecurity();

    Set<? extends IBaseCoding> getTag();

    String getVersionId();

    IBaseMetaType setVersionId(String theVersionId);

    /**
     * Returns the first tag (if any) that has the given system and code, or returns
     * <code>null</code> if none
     */
    IBaseCoding getTag(String theSystem, String theCode);

    /**
     * Returns the first security label (if any) that has the given system and code, or returns
     * <code>null</code> if none
     */
    IBaseCoding getSecurity(String theSystem, String theCode);

}
