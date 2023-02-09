package de.rki.demis.fhir.util.converter;

import de.rki.demis.fhir.model.udt.Enumeration;
import de.rki.demis.fhir.util.fhir_object.enums.BundleType;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

public class EnumerationToEnumBundleTypeConverter implements Converter<Enumeration<?>, Enumeration<BundleType>> {

    @Override
    public Enumeration<BundleType> convert(@NotNull Enumeration<?> source) {
        Enumeration<BundleType> out = new Enumeration<BundleType>();
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
