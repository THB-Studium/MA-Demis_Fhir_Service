package de.rki.demis.fhir.transfert.bundle;

//public class Bundle2BundleMod {
//
//    @Nullable
//    public static BundleMod apply(Bundle in) {
//        if (Objects.isNull(in)) {
//            return null;
//        }
//
//        BundleMod out = new BundleMod();
//
//        // Resource type attributes
//        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
//        out.setResourceType(in.getResourceType().toString());
//        out.setMeta(MetaFhir2Meta.apply(in.getMeta()));
//        out.setImplicitRules(UriTypeFhir2UriType.apply(in.getImplicitRulesElement()));
//        out.setLanguage(CodeTypeFhir2CodeType.apply(in.getLanguageElement()));
//
//        // Base type attributes
//        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
//        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));
//
//        // BundleMod type attributes
//        out.setIdentifier(IdentifierFhir2Identifier.apply(in.getIdentifier()));
//        out.setType(new Enumeration<BundleType>());
//        out.getType().setMyStringValue(Objects.nonNull(in.getType()) ? in.getType().getDisplay() : null);
//        out.setTimestamp(in.getTimestamp());
//        out.setTotal(in.getTotal());
//        out.setLink(new HashSet<>(BundleLinkComponentFhir2BundleLinkComponent.apply(in.getLink())));
//        out.setEntry(new HashSet<>(BundleEntryComponentFhir2BundleEntryComponent.apply(in.getEntry())));
//        out.setSignature(SignatureFhir2Signature.apply(in.getSignature()));
//
//        return out;
//    }
//
//    public static List<BundleMod> apply(@NotNull List<Bundle> in) {
//        return in.stream().map(Bundle2BundleMod::apply).collect(Collectors.toList());
//    }
//
//    public static Set<BundleMod> apply(@NotNull Set<Bundle> in) {
//        return in.stream().map(Bundle2BundleMod::apply).collect(Collectors.toSet());
//    }
//}
