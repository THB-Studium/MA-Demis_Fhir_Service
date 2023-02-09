package de.rki.demis.fhir.util.converter;

import de.rki.demis.fhir.model.udt.Enumeration;
import de.rki.demis.fhir.util.fhir_object.enums.HTTPVerb;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

public class EnumerationToEnumHTTPVerbConverter implements Converter<Enumeration<?>, Enumeration<HTTPVerb>> {

    @Override
    public Enumeration<HTTPVerb> convert(@NotNull Enumeration<?> source) {
        Enumeration<HTTPVerb> out = new Enumeration<HTTPVerb>();
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
