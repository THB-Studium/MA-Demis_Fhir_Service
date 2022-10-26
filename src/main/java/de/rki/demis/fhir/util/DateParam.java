package de.rki.demis.fhir.util;

import de.rki.demis.fhir.util.constant.DateFormatter;

import javax.ws.rs.WebApplicationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public class DateParam {
    private Date date;

    public DateParam(String in) throws WebApplicationException {
        if (!in.equals(null) && !in.equals("null")) {
            DateTimeFormatter dateFormatting = DateTimeFormatter.ofPattern(DateFormatter.DATE_TIME, Locale.GERMAN);
            DateFormat formatter = new SimpleDateFormat(DateFormatter.DATE_TIME);

//                String formattedDate = dateFormatting.print(Long.parseLong(in));
//                String formattedDate = dateFormatting.parse();
//                date = formatter.parse(formattedDate);


//                LocalDate date =
//                        Instant.ofEpochMilli(Long.parseLong(in)).atZone(ZoneId.systemDefault()).toLocalDate();

//                LocalDateTime date =
//                        LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(in)), ZoneId.systemDefault());


//                date = formatter.parse(formattedDate);

            date = new Date(Long.parseLong(in));
        }
    }

    public Date getDate() {
        return date;
    }

    public String format() {
        return date.toString();
    }

}
