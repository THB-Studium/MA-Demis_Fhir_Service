package de.rki.demis.fhir.util;

import lombok.experimental.UtilityClass;
import org.hl7.fhir.r4.model.Coding;

import java.util.List;

@UtilityClass
public class Tag2String {
    public static String tag2Sting(List<Coding> tag) {
        StringBuilder sb = new StringBuilder();

        tag.stream().forEach(item -> {
            sb.append(item);
            sb.append("\t");
        });

        return sb.toString();
    }
}
