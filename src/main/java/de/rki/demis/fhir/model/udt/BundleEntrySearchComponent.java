package de.rki.demis.fhir.model.udt;

import de.rki.demis.fhir.util.fhir_object.classes.BackboneElement;
import de.rki.demis.fhir.util.fhir_object.enums.SearchEntryMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@UserDefinedType(value = "BundleEntrySearchComponentUDT")
public class BundleEntrySearchComponent extends BackboneElement implements Serializable {
    @Serial
    private static final long serialVersionUID = 837739866L;


    /***
     * shortDefinition = "match | include | outcome - why this is in the result set",
     *           value = "Why this entry is in the result set - whether it's included as a match or because of an _include
     *                    requirement, or to convey information or warning information about the search process."
     ***/
    @CassandraType(type = CassandraType.Name.UDT, userTypeName = "enumeration_searchentrymode_udt")
    private Enumeration<SearchEntryMode> mode;

    /***
     * shortDefinition = "Search ranking (between 0 and 1)",
     *           value = "When searching, the server's search ranking score for the entry."
     ***/
    private BigDecimal score;
}
