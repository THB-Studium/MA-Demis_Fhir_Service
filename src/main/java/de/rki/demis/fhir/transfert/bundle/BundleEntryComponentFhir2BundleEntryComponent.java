//package de.rki.demis.fhir.transfert.bundle;
//
//import de.rki.demis.fhir.model.BundleEntryComponent;
//import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
//import de.rki.demis.fhir.transfert.resource.ResourceFhir2Resource;
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
//public class BundleEntryComponentFhir2BundleEntryComponent {
//
//    @Nullable
//    public static BundleEntryComponent apply(org.hl7.fhir.r4.model.Bundle.BundleEntryComponent in) {
//
//        if (Objects.isNull(in)) {
//            return null;
//        }
//
//        BundleEntryComponent out = new BundleEntryComponent();
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
//        // BundleEntryComponent type attributes
//        out.setLink(new HashSet<>(BundleLinkComponentFhir2BundleLinkComponent.apply(in.getLink())));
//        out.setFullUrl(UriTypeFhir2UriType.apply(in.getFullUrlElement()));
//        out.setResource(ResourceFhir2Resource.apply(in.getResource()));
////        out.setSearch();
////        out.setRequest();
////        out.setResponse();
//
//        return out;
//    }
//
//    public static List<BundleEntryComponent> apply(@NotNull List<org.hl7.fhir.r4.model.Bundle.BundleEntryComponent> in) {
//        return in.stream().map(BundleEntryComponentFhir2BundleEntryComponent::apply).collect(Collectors.toList());
//    }
//
//    public static Set<BundleEntryComponent> apply(@NotNull Set<org.hl7.fhir.r4.model.Bundle.BundleEntryComponent> in) {
//        return in.stream().map(BundleEntryComponentFhir2BundleEntryComponent::apply).collect(Collectors.toSet());
//    }
//}
