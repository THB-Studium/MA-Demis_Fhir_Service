package de.rki.demis.fhir.transfert.type;

import de.rki.demis.fhir.model.udt.Type;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class TypeFhir2Type {

    @Nullable
    public static Type apply(org.hl7.fhir.r4.model.Type in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Type out = new Type();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        return out;
    }

    public static List<Type> apply(@NotNull List<org.hl7.fhir.r4.model.Type> in) {
        return in.stream().map(TypeFhir2Type::apply).collect(Collectors.toList());
    }

    public static Set<Type> apply(@NotNull Set<org.hl7.fhir.r4.model.Type> in) {
        return in.stream().map(TypeFhir2Type::apply).collect(Collectors.toSet());
    }
}
