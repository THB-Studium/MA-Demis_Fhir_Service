package de.rki.demis.fhir.model;

import de.rki.demis.fhir.util.fhir_object.enums.SearchEntryMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class BundleEntrySearchComponent extends BackboneElement implements Serializable, BaseEntity {
    @Serial
    private static final long serialVersionUID = 837739866L;


    /***
     * shortDefinition = "match | include | outcome - why this is in the result set",
     *           value = "Why this entry is in the result set - whether it's included as a match or because of an _include
     *                    requirement, or to convey information or warning information about the search process."
     ***/
    @OneToOne
    private Enumeration<SearchEntryMode> mode;

    /***
     * shortDefinition = "Search ranking (between 0 and 1)",
     *           value = "When searching, the server's search ranking score for the entry."
     ***/
    private BigDecimal score;
}
