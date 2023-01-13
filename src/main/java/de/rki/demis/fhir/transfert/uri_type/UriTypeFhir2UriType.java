package de.rki.demis.fhir.transfert.uri_type;

import de.rki.demis.fhir.model.UriType;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UriTypeFhir2UriType {

    @Nullable
    public static UriType apply(org.hl7.fhir.r4.model.UriType in) {

        if (Objects.isNull(in)) {
            return null;
        }

        UriType out = new UriType();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
//        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // PrimitiveType<T> attributes
        out.setMyStringValue(in.getValueAsString());
//        out.setMyCoercedValue(in.getValue());

        return out;
    }

    public static List<UriType> apply(@NotNull List<org.hl7.fhir.r4.model.UriType> in) {
        return in.stream().map(UriTypeFhir2UriType::apply).collect(Collectors.toList());
    }

    public static Set<UriType> apply(@NotNull Set<org.hl7.fhir.r4.model.UriType> in) {
        return in.stream().map(UriTypeFhir2UriType::apply).collect(Collectors.toSet());
    }
}
