package de.rki.demis.fhir.util.fhir_object.classes;

import ca.uhn.fhir.i18n.Msg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.defaultString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Include implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String myValue;
    private boolean myIterate;
    private boolean myImmutable;
    private String myParamType;
    private String myParamName;
    private String myParamTargetType;

    public Include(String theValue, boolean theIterate) {
        this(theValue, theIterate, false);
    }

    public Include(String theValue, boolean theIterate, boolean theImmutable) {
        setValue(theValue);
        this.myIterate = theIterate;
        this.myImmutable = theImmutable;
    }

    public void setValue(String theValue) {
        if (myImmutable) {
            throw new IllegalStateException(Msg.code(1888) + "Can not change the value of this include");
        }

        String value = defaultString(theValue);

        int firstColon = value.indexOf(':');
        String paramType;
        String paramName;
        String paramTargetType;
        if (firstColon == -1 || firstColon == value.length() - 1) {
            paramType = null;
            paramName = null;
            paramTargetType = null;
        } else {
            paramType = value.substring(0, firstColon);
            int secondColon = value.indexOf(':', firstColon + 1);
            if (secondColon == -1) {
                paramName = value.substring(firstColon + 1);
                paramTargetType = null;
            } else {
                paramName = value.substring(firstColon + 1, secondColon);
                paramTargetType = value.substring(secondColon + 1);
            }
        }

        myParamType = paramType;
        myParamName = paramName;
        myParamTargetType = paramTargetType;
        myValue = theValue;

    }

    public Include toLocked() {
        return new Include(myValue, myIterate, true);
    }
}
