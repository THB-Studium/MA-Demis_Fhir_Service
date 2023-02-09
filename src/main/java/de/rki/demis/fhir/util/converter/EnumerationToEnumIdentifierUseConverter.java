package de.rki.demis.fhir.util.converter;

import de.rki.demis.fhir.model.udt.Enumeration;
import de.rki.demis.fhir.util.fhir_object.enums.BundleType;
import de.rki.demis.fhir.util.fhir_object.enums.IdentifierUse;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

public class EnumerationToEnumIdentifierUseConverter implements Converter<Enumeration<?>, Enumeration<IdentifierUse>> {

    @Override
    public Enumeration<IdentifierUse> convert(@NotNull Enumeration<?> source) {
        Enumeration<IdentifierUse> out = new Enumeration<IdentifierUse>();
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
