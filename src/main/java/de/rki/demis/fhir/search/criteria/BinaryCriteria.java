package de.rki.demis.fhir.search.criteria;

import lombok.Data;

import java.util.Date;

@Data
public class BinaryCriteria {
    private String tag;
    private Date lastUpdated;
}
