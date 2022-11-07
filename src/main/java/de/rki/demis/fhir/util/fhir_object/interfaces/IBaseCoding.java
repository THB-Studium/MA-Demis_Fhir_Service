package de.rki.demis.fhir.util.fhir_object.interfaces;

public interface IBaseCoding extends IBase {

    String getCode();

    IBaseCoding setCode(String theTerm);

    String getDisplay();

    IBaseCoding setDisplay(String theLabel);

    String getSystem();

    IBaseCoding setSystem(String theScheme);

}
