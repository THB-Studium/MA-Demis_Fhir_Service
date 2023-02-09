package de.rki.demis.fhir.transfert.bundle;

import de.rki.demis.fhir.model.udt.BundleEntryResponseComponent;
import de.rki.demis.fhir.model.udt.UriType;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import de.rki.demis.fhir.transfert.resource.ResourceFhir2Resource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class BundleEntryResponseComponentFhir2BundleEntryResponseComponent {

    @Nullable
    public static BundleEntryResponseComponent apply(org.hl7.fhir.r4.model.Bundle.BundleEntryResponseComponent in) {

        if (Objects.isNull(in)) {
            return null;
        }

        BundleEntryResponseComponent out = new BundleEntryResponseComponent();

        // BackboneElement type attributes
        out.setModifierExtension(new HashSet<>(ExtensionFhir2Extension.apply(in.getModifierExtension())));

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // BundleEntryResponseComponent type attributes
        out.setStatus(in.getStatus());

        out.setLocation(new UriType());
        out.getLocation().setMyStringValue(in.getLocation());

        out.setEtag(in.getEtag());
        out.setLastModified(in.getLastModified());
//        out.setOutcome(ResourceFhir2Resource.apply(in.getOutcome()));

        return out;
    }

    public static List<BundleEntryResponseComponent> apply(@NotNull List<org.hl7.fhir.r4.model.Bundle.BundleEntryResponseComponent> in) {
        return in.stream().map(BundleEntryResponseComponentFhir2BundleEntryResponseComponent::apply).collect(Collectors.toList());
    }

    public static Set<BundleEntryResponseComponent> apply(@NotNull Set<org.hl7.fhir.r4.model.Bundle.BundleEntryResponseComponent> in) {
        return in.stream().map(BundleEntryResponseComponentFhir2BundleEntryResponseComponent::apply).collect(Collectors.toSet());
    }
}
