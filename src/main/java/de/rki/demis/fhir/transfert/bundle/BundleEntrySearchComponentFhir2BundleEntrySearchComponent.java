package de.rki.demis.fhir.transfert.bundle;

import de.rki.demis.fhir.model.udt.BundleEntrySearchComponent;
import de.rki.demis.fhir.model.udt.Enumeration;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import de.rki.demis.fhir.util.fhir_object.enums.SearchEntryMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class BundleEntrySearchComponentFhir2BundleEntrySearchComponent {

    @Nullable
    public static BundleEntrySearchComponent apply(org.hl7.fhir.r4.model.Bundle.BundleEntrySearchComponent in) {

        if (Objects.isNull(in)) {
            return null;
        }

        BundleEntrySearchComponent out = new BundleEntrySearchComponent();

        // BackboneElement type attributes
        out.setModifierExtension(new HashSet<>(ExtensionFhir2Extension.apply(in.getModifierExtension())));

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // BundleEntrySearchComponent type attributes
//        out.setMode(new Enumeration<SearchEntryMode>());
//        out.getMode().setMyStringValue(Objects.nonNull(in.getMode()) ? in.getMode().getDisplay() : null);
        out.setMode(null); // todo

        out.setScore(in.getScore());

        return out;
    }

    public static List<BundleEntrySearchComponent> apply(@NotNull List<org.hl7.fhir.r4.model.Bundle.BundleEntrySearchComponent> in) {
        return in.stream().map(BundleEntrySearchComponentFhir2BundleEntrySearchComponent::apply).collect(Collectors.toList());
    }

    public static Set<BundleEntrySearchComponent> apply(@NotNull Set<org.hl7.fhir.r4.model.Bundle.BundleEntrySearchComponent> in) {
        return in.stream().map(BundleEntrySearchComponentFhir2BundleEntrySearchComponent::apply).collect(Collectors.toSet());
    }
}
