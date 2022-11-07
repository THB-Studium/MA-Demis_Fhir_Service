package de.rki.demis.fhir.util.fhir_object.interfaces;

import ca.uhn.fhir.context.FhirVersionEnum;
import de.rki.demis.fhir.util.fhir_object.classes.Include;

import java.util.Collections;
import java.util.Set;

public interface IBaseResource extends IBase, IElement {

    /**
     * Include constant for <code>*</code> (return all includes)
     */
    Include INCLUDE_ALL = new Include("*", false).toLocked();

    /**
     * Include set containing only {@link #INCLUDE_ALL}
     */
    Set<Include> WILDCARD_ALL_SET = Set.copyOf(Collections.singletonList(INCLUDE_ALL));

    IBaseMetaType getMeta();

    FhirVersionEnum getStructureFhirVersionEnum();

}
