package de.rki.demis.fhir.transfert.bundle;

import de.rki.demis.fhir.model.table.BundleMod;
import de.rki.demis.fhir.transfert.code_type.CodeTypeFhir2CodeType;
import de.rki.demis.fhir.transfert.meta.MetaFhir2Meta;
import de.rki.demis.fhir.transfert.uri_type.UriTypeFhir2UriType;
import org.hl7.fhir.r4.model.Bundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Bundle2BundleMod {

    @Nullable
    public static BundleMod apply(Bundle in) {
        if (Objects.isNull(in)) {
            return null;
        }

        BundleMod out = new BundleMod();

        // Resource type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setResourceType(in.getResourceType().toString());
        out.setMeta(MetaFhir2Meta.apply(in.getMeta()));
        out.setImplicitRules(UriTypeFhir2UriType.apply(in.getImplicitRulesElement()));
        out.setLanguage(CodeTypeFhir2CodeType.apply(in.getLanguageElement()));

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // BundleMod type attributes todo
//        out.setIdentifier(IdentifierFhir2Identifier.apply(in.getIdentifier()));
//        out.setType(new Enumeration<BundleType>());
//        out.getType().setMyStringValue(Objects.nonNull(in.getType()) ? in.getType().getDisplay() : null);
//        out.setTimestamp(in.getTimestamp());
//        out.setTotal(in.getTotal());
//        out.setLink(new HashSet<>(BundleLinkComponentFhir2BundleLinkComponent.apply(in.getLink())));
//        out.setEntry(new HashSet<>(BundleEntryComponentFhir2BundleEntryComponent.apply(in.getEntry())));
//        out.setSignature(SignatureFhir2Signature.apply(in.getSignature()));

        return out;
    }

    public static List<BundleMod> apply(@NotNull List<Bundle> in) {
        return in.stream().map(Bundle2BundleMod::apply).collect(Collectors.toList());
    }

    public static Set<BundleMod> apply(@NotNull Set<Bundle> in) {
        return in.stream().map(Bundle2BundleMod::apply).collect(Collectors.toSet());
    }
}
