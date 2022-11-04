package de.rki.demis.fhir.util.fhir_object.enums;

public enum SearchResultParam {
    CONTAINED("_contained"),
    CONTAINED_TYPE("_containedType"),
    COUNT("_count"),
    ELEMENTS("_elements"),
    INCLUDE("_include"),
    REV_INCLUDE("_revinclude"),
    SORT("_sort"),
    SUMMARY("_summary"),
    TOTAL("_total");

    public final String label;

    SearchResultParam(String label) {
        this.label = label;
    }
}
