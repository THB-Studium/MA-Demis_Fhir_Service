package de.rki.demis.fhir.util.fhir_object.interfaces;

public interface IBaseCoding extends IBase {

    String getCode();

    String getDisplay();

    String getSystem();

    org.hl7.fhir.instance.model.api.IBaseCoding setCode(String theTerm);

    org.hl7.fhir.instance.model.api.IBaseCoding setDisplay(String theLabel);

    org.hl7.fhir.instance.model.api.IBaseCoding setSystem(String theScheme);

}
