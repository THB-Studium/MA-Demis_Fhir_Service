package de.rki.demis.fhir.util;

import lombok.Data;

import javax.ws.rs.WebApplicationException;
import java.util.Objects;

@Data
public class TagQueryParser {
    private String system;
    private String code;
    private String display;

    public TagQueryParser(String in) throws WebApplicationException {
        if (Objects.nonNull(in)) {
            String[] splitString = in.split("\\|");
            system  = splitString.length >= 1 ? splitString[0] : null; // todo: add regExp for internet link validation
            code    = splitString.length >= 2 ? splitString[1] : null;
            display = splitString.length >= 3 ? splitString[2] : null;
        }
    }

}
