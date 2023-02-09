package de.rki.demis.fhir.util.converter;

import de.rki.demis.fhir.model.udt.Enumeration;
import de.rki.demis.fhir.util.fhir_object.enums.SearchEntryMode;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

public class EnumerationToEnumSearchEntryModeConverter implements Converter<Enumeration<?>, Enumeration<SearchEntryMode>> {

    @Override
    public Enumeration<SearchEntryMode> convert(@NotNull Enumeration<?> source) {
        Enumeration<SearchEntryMode> out = new Enumeration<SearchEntryMode>();
        out.setId(source.getId());
        out.setMyStringValue(source.getMyStringValue());
        out.setExtension(source.getExtension());
        out.setFormatCommentsPost(source.getFormatCommentsPost());
        out.setFormatCommentsPre(source.getFormatCommentsPre());
//        out.setMyCoercedValue(source.getMyCoercedValue());
        out.setUserData(source.getUserData());

        return out;
    }
}
