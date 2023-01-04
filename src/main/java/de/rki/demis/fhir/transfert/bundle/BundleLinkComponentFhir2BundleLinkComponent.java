//package de.rki.demis.fhir.transfert.bundle;
//
//import de.rki.demis.fhir.model.BundleLinkComponent;
//import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
//import de.rki.demis.fhir.transfert.uri_type.UriTypeFhir2UriType;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//public class BundleLinkComponentFhir2BundleLinkComponent {
//
//    @Nullable
//    public static BundleLinkComponent apply(org.hl7.fhir.r4.model.Bundle.BundleLinkComponent in) {
//
//        if (Objects.isNull(in)) {
//            return null;
//        }
//
//        BundleLinkComponent out = new BundleLinkComponent();
//
//        // Element type attributes
//        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
//        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
//        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());
//
//        // Base type attributes
//        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
//        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));
//
//        // BundleLinkComponent type attributes
//        out.setRelation(in.getRelation());
//        out.setUrl(UriTypeFhir2UriType.apply(in.getUrlElement()));
//
//        return out;
//    }
//
//    public static List<BundleLinkComponent> apply(@NotNull List<org.hl7.fhir.r4.model.Bundle.BundleLinkComponent> in) {
//        return in.stream().map(BundleLinkComponentFhir2BundleLinkComponent::apply).collect(Collectors.toList());
//    }
//
//    public static Set<BundleLinkComponent> apply(@NotNull Set<org.hl7.fhir.r4.model.Bundle.BundleLinkComponent> in) {
//        return in.stream().map(BundleLinkComponentFhir2BundleLinkComponent::apply).collect(Collectors.toSet());
//    }
//}
