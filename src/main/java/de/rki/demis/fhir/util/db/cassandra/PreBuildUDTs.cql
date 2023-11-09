-- Simples UDTs
create type if not exists demis_cassandra_db."CanonicalTypeUDT" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mycoercedvalue text,
    mystringvalue text,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."CodeTypeUDT" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mycoercedvalue text,
    mystringvalue text,
    system text,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."EnumerationUDT" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mycoercedvalue text,
    mystringvalue text,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."enumeration_bundletype_udt" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mycoercedvalue text,
    mystringvalue text,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."enumeration_searchentrymode_udt" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mycoercedvalue text,
    mystringvalue text,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."enumeration_identifieruse_udt" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mycoercedvalue text,
    mystringvalue text,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."enumeration_httpverb_udt" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mycoercedvalue text,
    mystringvalue text,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."type_udt" (
    userdata map<text, text>,
    formatcommentspre set<text>,
    formatcommentspost set<text>,
    disallowextensions boolean,
    id uuid,
    extension set<text>
    );

create type if not exists demis_cassandra_db."UriTypeUDT" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mycoercedvalue text,
    mystringvalue text,
    userdata map<text, text>
    );


-- Complex UDTs ::::::::::::::::::::::::::::::::::::::::::::::::::::::

create type if not exists demis_cassandra_db."CodingUDT" (
    code frozen<"CodeTypeUDT">,
    disallowextensions boolean,
    display text,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    system frozen<"UriTypeUDT">,
    userdata map<text, text>,
    userselected boolean,
    version text
    );

create type if not exists demis_cassandra_db."CodeableConceptUDT" (
    coding set<frozen<"CodingUDT">>,
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    text text,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."ExtensionUDT" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    url frozen<"UriTypeUDT">,
    userdata map<text, text>,
    value frozen<type_udt>
    );

create type if not exists demis_cassandra_db."BundleLinkComponentUDT" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    modifierextension set<frozen<"ExtensionUDT">>,
    relation text,
    url frozen<"UriTypeUDT">,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."BundleEntryRequestComponentUDT" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    ifmatch text,
    ifmodifiedsince timestamp,
    ifnoneexist text,
    ifnonematch text,
    method frozen<enumeration_httpverb_udt>,
    modifierextension set<frozen<"ExtensionUDT">>,
    url frozen<"UriTypeUDT">,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."BundleEntryResponseComponentUDT" (
    disallowextensions boolean,
    etag text,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    lastmodified timestamp,
    location frozen<"UriTypeUDT">,
    modifierextension set<frozen<"ExtensionUDT">>,
    outcome frozen<resource_udt>,
    status text,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."BundleEntrySearchComponentUDT" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mode frozen<enumeration_searchentrymode_udt>,
    modifierextension set<frozen<"ExtensionUDT">>,
    score decimal,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."MetaUDT" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    lastupdated timestamp,
    profile set<frozen<"CanonicalTypeUDT">>,
    security set<frozen<"CodingUDT">>,
    source frozen<"UriTypeUDT">,
    tag set<frozen<"CodingUDT">>,
    userdata map<text, text>,
    versionid text
    );

create type if not exists demis_cassandra_db."resource_udt" (
    userdata map<text, text>,
    formatcommentspre set<text>,
    formatcommentspost set<text>,
    id uuid,
    resourcetype text,
    meta frozen<"MetaUDT">,
    implicitrules frozen<"UriTypeUDT">,
    language frozen<"CodeTypeUDT">
    );

create type if not exists demis_cassandra_db."BundleEntryComponentUDT" (
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    fullurl frozen<"UriTypeUDT">,
    id uuid,
    link set<frozen<"BundleLinkComponentUDT">>,
    modifierextension set<frozen<"ExtensionUDT">>,
    request frozen<"BundleEntryRequestComponentUDT">,
    resource frozen<resource_udt>,
    response frozen<"BundleEntryResponseComponentUDT">,
    search frozen<"BundleEntrySearchComponentUDT">,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."IdentifierUDT" (
    assigner text,
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    period text,
    system frozen<"UriTypeUDT">,
    type frozen<"CodeableConceptUDT">,
    "use" frozen<enumeration_identifieruse_udt>,
    userdata map<text, text>,
    value text
    );

create type if not exists demis_cassandra_db."ReferenceUDT" (
    disallowextensions boolean,
    display text,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    identifier frozen<"IdentifierUDT">,
    reference text,
    type frozen<"UriTypeUDT">,
    userdata map<text, text>
    );

create type if not exists demis_cassandra_db."SignatureUDT" (
    data text,
    disallowextensions boolean,
    extension set<text>,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    onbehalfof frozen<"ReferenceUDT">,
    onbehalfoftarget frozen<resource_udt>,
    sigformat frozen<"CodeTypeUDT">,
    targetformat frozen<"CodeTypeUDT">,
    type set<frozen<"CodingUDT">>,
    userdata map<text, text>,
    when timestamp,
    who frozen<"ReferenceUDT">,
    whotarget frozen<resource_udt>
    );



