package de.rki.demis.fhir.util.fhir_object.enums;

public enum SearchDatePrefix {
    /*** approximately the same to the provided value ***/
    AP("ap"),

    /*** ends before the provided value ***/
    EB("eb"),

    /*** equal to the provided value ***/
    EQ("eq"),

    /*** greater or equal to the provided value ***/
    GE("ge"),

    /*** greater than the provided value ***/
    GT("gt"),

    /*** less or equal to the provided value ***/
    LE("le"),

    /*** less than the provided value ***/
    LT("lt"),

    /*** not equal to the provided value ***/
    NE("ne"),

    /*** starts after the provided value ***/
    SA("sa");

    public final String label;

    SearchDatePrefix(String label) {
        this.label = label;
    }
}
