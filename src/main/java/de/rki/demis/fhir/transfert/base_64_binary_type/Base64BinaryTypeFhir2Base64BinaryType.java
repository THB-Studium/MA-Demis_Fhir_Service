package de.rki.demis.fhir.transfert.base_64_binary_type;

import de.rki.demis.fhir.model.Base64BinaryType;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Base64BinaryTypeFhir2Base64BinaryType {

    @Nullable
    public static Base64BinaryType apply(org.hl7.fhir.r4.model.Base64BinaryType in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Base64BinaryType out = new Base64BinaryType();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
//        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
//        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // PrimitiveType<T> attributes
        out.setMyStringValue(in.getValueAsString());
//        out.setMyCoercedValue(in.getValue());

        // Base64BinaryType type attributes
        out.setMyValue(in.getValue());

        return out;
    }

    public static List<Base64BinaryType> apply(@NotNull List<org.hl7.fhir.r4.model.Base64BinaryType> in) {
        return in.stream().map(Base64BinaryTypeFhir2Base64BinaryType::apply).collect(Collectors.toList());
    }

    public static Set<Base64BinaryType> apply(@NotNull Set<org.hl7.fhir.r4.model.Base64BinaryType> in) {
        return in.stream().map(Base64BinaryTypeFhir2Base64BinaryType::apply).collect(Collectors.toSet());
    }
}
