package de.rki.demis.fhir.search.criteria;

import de.rki.demis.fhir.util.fhir_object.enums.SearchDatePrefix;
import lombok.Data;

import java.util.Date;

@Data
public class BinaryCriteria {
    // tag:....
    private String system;
    private String code;
    private String display;

    // lastUpdated:....
    private Date lastUpdated;
    private SearchDatePrefix searchDateOp;
}
