package de.rki.demis.fhir.transfert.canonical_type;

import de.rki.demis.fhir.model.udt.CanonicalType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CanonicalTypeFhir2CanonicalType {

    @Nullable
    public static CanonicalType apply(org.hl7.fhir.r4.model.CanonicalType in) {

        if (Objects.isNull(in)) {
            return null;
        }

        CanonicalType out = new CanonicalType();

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

    public static List<CanonicalType> apply(@NotNull List<org.hl7.fhir.r4.model.CanonicalType> in) {
        return in.stream().map(CanonicalTypeFhir2CanonicalType::apply).collect(Collectors.toList());
    }

    public static Set<CanonicalType> apply(@NotNull Set<org.hl7.fhir.r4.model.CanonicalType> in) {
        return in.stream().map(CanonicalTypeFhir2CanonicalType::apply).collect(Collectors.toSet());
    }
}
