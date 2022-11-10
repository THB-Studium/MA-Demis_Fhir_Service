package de.rki.demis.fhir.transfert.reference;

import de.rki.demis.fhir.model.Reference;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import de.rki.demis.fhir.transfert.identifier.IdentifierFhir2Identifier;
import de.rki.demis.fhir.transfert.uri_type.UriTypeFhir2UriType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReferenceFhir2Reference {

    @Nullable
    public static Reference apply(org.hl7.fhir.r4.model.Reference in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Reference out = new Reference();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // Reference type attributes
        out.setReference(in.getReference());
        out.setType(UriTypeFhir2UriType.apply(in.getTypeElement()));
        out.setIdentifier(IdentifierFhir2Identifier.apply(in.getIdentifier()));
        out.setDisplay(in.getDisplay());

        return out;
    }

    public static List<Reference> apply(@NotNull List<org.hl7.fhir.r4.model.Reference> in) {
        return in.stream().map(ReferenceFhir2Reference::apply).collect(Collectors.toList());
    }

    public static Set<Reference> apply(@NotNull Set<org.hl7.fhir.r4.model.Reference> in) {
        return in.stream().map(ReferenceFhir2Reference::apply).collect(Collectors.toSet());
    }
}
