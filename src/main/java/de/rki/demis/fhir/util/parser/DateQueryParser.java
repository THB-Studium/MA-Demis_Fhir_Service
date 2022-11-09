package de.rki.demis.fhir.util.parser;

import de.rki.demis.fhir.util.fhir_object.enums.SearchDatePrefix;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ws.rs.WebApplicationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import static de.rki.demis.fhir.util.constant.DateFormatter.DATE_TIME_FORMAT;
import static de.rki.demis.fhir.util.constant.DateFormatter.TIMEZONE_GE;

@Data
public class DateQueryParser {
    private SearchDatePrefix prefix;
    private Date date;

    public DateQueryParser(String in) throws WebApplicationException {
        if (Objects.nonNull(in)) {
            prefix = SearchDatePrefix.valueOf(Objects.requireNonNull(extractPrefix(in)));
            date = extractDate(in);
        }
    }

    public static Date string2DateParser(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.GERMAN);
        formatter.setTimeZone(TimeZone.getTimeZone(TIMEZONE_GE));

        try {
            return formatter.parse(dateInString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    private String extractPrefix(@NotNull String in) {
        return in.length() > 2 ? in.substring(0, 2).toUpperCase() : null;
    }

    private Date extractDate(String in) {
        String dateInString = Objects.nonNull(prefix) ? in.split(prefix.label)[1] : in;
        return string2DateParser(dateInString);
    }

}
