package de.rki.demis.fhir.transfert.extension;

import de.rki.demis.fhir.model.udt.Extension;
import de.rki.demis.fhir.transfert.type.TypeFhir2Type;
import de.rki.demis.fhir.transfert.uri_type.UriTypeFhir2UriType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExtensionFhir2Extension {

    @Nullable
    public static Extension apply(org.hl7.fhir.r4.model.Extension in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Extension out = new Extension();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // Extension type attributes
        out.setUrl(UriTypeFhir2UriType.apply(in.getUrlElement()));
        out.setValue(TypeFhir2Type.apply(in.getValue()));


        return out;
    }

    public static List<Extension> apply(@NotNull List<org.hl7.fhir.r4.model.Extension> in) {
        return in.stream().map(ExtensionFhir2Extension::apply).collect(Collectors.toList());
    }

    public static Set<Extension> apply(@NotNull Set<org.hl7.fhir.r4.model.Extension> in) {
        return in.stream().map(ExtensionFhir2Extension::apply).collect(Collectors.toSet());
    }
}
