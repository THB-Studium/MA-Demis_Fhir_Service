package de.rki.demis.fhir.util.fhir_object.classes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Configuration {

    private static int status = 0; // 0: auto-create // 1: error // 2: return null

    @Getter
    private static boolean acceptInvalidEnums;


    public static boolean errorOnAutoCreate() {
        return status == 1;
    }

    public static boolean doAutoCreate() {
        return status == 0;
    }

    public static void setAcceptInvalidEnums(boolean value) {
        acceptInvalidEnums = value;
    }
}
