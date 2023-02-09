package de.rki.demis.fhir.transfert.bundle;

import de.rki.demis.fhir.model.udt.BundleEntryRequestComponent;
import de.rki.demis.fhir.model.udt.Enumeration;
import de.rki.demis.fhir.model.udt.UriType;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import de.rki.demis.fhir.util.fhir_object.enums.HTTPVerb;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class BundleEntryRequestComponentFhir2BundleEntryRequestComponent {

    @Nullable
    public static BundleEntryRequestComponent apply(org.hl7.fhir.r4.model.Bundle.BundleEntryRequestComponent in) {

        if (Objects.isNull(in)) {
            return null;
        }

        BundleEntryRequestComponent out = new BundleEntryRequestComponent();

        // BackboneElement type attributes
        out.setModifierExtension(new HashSet<>(ExtensionFhir2Extension.apply(in.getModifierExtension())));

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // BundleEntryRequestComponent type attributes
//        out.setMethod(new Enumeration<HTTPVerb>());
//        out.getMethod().setMyStringValue(Objects.nonNull(in.getMethod()) ? in.getMethod().getDisplay() : null);
        out.setMethod(null); // todo

        out.setUrl(new UriType());
        out.getUrl().setMyStringValue(in.getUrl());

        out.setIfNoneMatch(in.getIfNoneMatch());
        out.setIfModifiedSince(in.getIfModifiedSince());
        out.setIfMatch(in.getIfMatch());
        out.setIfNoneExist(in.getIfNoneExist());

        return out;
    }

    public static List<BundleEntryRequestComponent> apply(@NotNull List<org.hl7.fhir.r4.model.Bundle.BundleEntryRequestComponent> in) {
        return in.stream().map(BundleEntryRequestComponentFhir2BundleEntryRequestComponent::apply).collect(Collectors.toList());
    }

    public static Set<BundleEntryRequestComponent> apply(@NotNull Set<org.hl7.fhir.r4.model.Bundle.BundleEntryRequestComponent> in) {
        return in.stream().map(BundleEntryRequestComponentFhir2BundleEntryRequestComponent::apply).collect(Collectors.toSet());
    }
}
