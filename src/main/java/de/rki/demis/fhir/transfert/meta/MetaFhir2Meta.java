package de.rki.demis.fhir.transfert.meta;

import de.rki.demis.fhir.model.udt.Meta;
import de.rki.demis.fhir.transfert.canonical_type.CanonicalTypeFhir2CanonicalType;
import de.rki.demis.fhir.transfert.coding.CodingFhir2Coding;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import de.rki.demis.fhir.transfert.uri_type.UriTypeFhir2UriType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class MetaFhir2Meta {

    @Nullable
    public static Meta apply(org.hl7.fhir.r4.model.Meta in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Meta out = new Meta();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // Meta type attributes
        out.setVersionId(in.getVersionId());
        out.setLastUpdated(new Date());
        out.setSource(UriTypeFhir2UriType.apply(in.getSourceElement()));
        out.setProfile(new HashSet<>(CanonicalTypeFhir2CanonicalType.apply(in.getProfile())));
        out.setSecurity(new HashSet<>(CodingFhir2Coding.apply(in.getSecurity())));
        out.setTag(new HashSet<>(CodingFhir2Coding.apply(in.getTag())));

        return out;
    }

    public static List<Meta> apply(@NotNull List<org.hl7.fhir.r4.model.Meta> in) {
        return in.stream().map(MetaFhir2Meta::apply).collect(Collectors.toList());
    }

    public static Set<Meta> apply(@NotNull Set<org.hl7.fhir.r4.model.Meta> in) {
        return in.stream().map(MetaFhir2Meta::apply).collect(Collectors.toSet());
    }
}
