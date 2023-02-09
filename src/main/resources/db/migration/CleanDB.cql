-- Tables

drop table if exists binarymod;
drop table if exists bundlemod;

-- UDTs

drop type if exists "SignatureUDT";
drop type if exists "ReferenceUDT";
drop type if exists "IdentifierUDT";
drop type if exists "CodeableConceptUDT";
drop type if exists "BundleEntryComponentUDT";
drop type if exists "BundleEntrySearchComponentUDT";
drop type if exists "BundleEntryRequestComponentUDT";
drop type if exists "BundleEntryResponseComponentUDT";
drop type if exists "BundleLinkComponentUDT";
drop type if exists resource_udt;
drop type if exists "MetaUDT";
drop type if exists "CodingUDT";
drop type if exists "ExtensionUDT";
drop type if exists "CodeTypeUDT";
drop type if exists "UriTypeUDT";
drop type if exists "CanonicalTypeUDT";
drop type if exists enumeration_bundletype_udt;
drop type if exists enumeration_httpverb_udt;
drop type if exists enumeration_identifieruse_udt;
drop type if exists enumeration_searchentrymode_udt;
drop type if exists type_udt;
