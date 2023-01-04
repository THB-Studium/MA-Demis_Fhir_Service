package de.rki.demis.fhir.controller;

import static de.rki.demis.fhir.util.constant.Constants.BINARY_ID;
import static de.rki.demis.fhir.util.constant.Constants.BUNDLE_ID;
import static de.rki.demis.fhir.util.constant.Constants.PATIENT_ID;
import static de.rki.demis.fhir.util.constant.Constants.SEARCH;

public class ApiConstants {

    // API
    public static final String API               = "api/fhir";

    // Cross-Origin:
    public static final String CROSS_ORIGIN_PATH = "*";

    // Binaries:
    public static final String BINARIES_ROOT     = API + "/binaries";
    public static final String BINARIES_SEARCH   = BINARIES_ROOT + "/" + SEARCH;
    public static final String BINARIES_ITEM     = BINARIES_ROOT + "/{" + BINARY_ID + "}";

    // Bundles:
    public static final String BUNDLES_ROOT      = API + "/bundles";
    public static final String BUNDLES_SEARCH    = BUNDLES_ROOT + "/" + SEARCH;
    public static final String BUNDLES_ITEM      = BUNDLES_ROOT + "/{" + BUNDLE_ID + "}";

    // Patients:
    public static final String PATIENTS_ROOT     = API + "/patients";
    public static final String PATIENTS_SEARCH   = PATIENTS_ROOT + "/" + SEARCH;
    public static final String PATIENTS_ITEM     = PATIENTS_ROOT + "/{" + PATIENT_ID + "}";

}
