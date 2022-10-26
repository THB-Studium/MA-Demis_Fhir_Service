package de.rki.demis.fhir.util.fhir_object.interfaces;

import java.util.Date;
import java.util.List;

public interface IBaseMetaType extends ICompositeType {

    org.hl7.fhir.instance.model.api.IBaseMetaType addProfile(String theProfile);

    IBaseCoding addSecurity();

    IBaseCoding addTag();

    Date getLastUpdated();

    List<? extends IPrimitiveType<String>> getProfile();

    List<? extends IBaseCoding> getSecurity();

    List<? extends IBaseCoding> getTag();

    String getVersionId();

    org.hl7.fhir.instance.model.api.IBaseMetaType setLastUpdated(Date theHeaderDateValue);

    org.hl7.fhir.instance.model.api.IBaseMetaType setVersionId(String theVersionId);

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
