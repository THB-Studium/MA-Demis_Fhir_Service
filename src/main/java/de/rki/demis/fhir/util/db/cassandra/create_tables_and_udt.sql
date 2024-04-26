create type "CanonicalTypeUDT" (
    disallowextensions boolean,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mystringvalue text,
    userdata map<text, text>
    );

create type "CodeTypeUDT" (
    disallowextensions boolean,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mystringvalue text,
    system text,
    userdata map<text, text>
    );

create type "CodingUDT" (
    code frozen<"CodeTypeUDT">,
    disallowextensions boolean,
    display text,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    system frozen<"UriTypeUDT">,
    userdata map<text, text>,
    userselected boolean,
    version text
    );

create type "MetaUDT" (
    disallowextensions boolean,
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

create type "ReferenceUDT" (
    disallowextensions boolean,
    display text,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    reference text,
    type frozen<"UriTypeUDT">,
    userdata map<text, text>
    );

create type "UriTypeUDT" (
    disallowextensions boolean,
    formatcommentspost set<text>,
    formatcommentspre set<text>,
    id uuid,
    mystringvalue text,
    userdata map<text, text>
    );

create table "BinaryMod"
(
    id                    uuid primary key,
    contenttype           text,
    data                  text,
    formatcommentspost    set<text>,
    formatcommentspre     set<text>,
    implicitrules         frozen<"UriTypeUDT">,
    language              frozen<"CodeTypeUDT">,
    meta                  frozen<"MetaUDT">,
    resourcetype          text,
    securitycontext       frozen<"ReferenceUDT">,
    securitycontexttarget uuid,
    userdata              map<text, text>
);

create table "BundleMod"
(
    id                 uuid primary key,
    formatcommentspost set<text>,
    formatcommentspre  set<text>,
    implicitrules      frozen<"UriTypeUDT">,
    language           frozen<"CodeTypeUDT">,
    meta               frozen<"MetaUDT">,
    resourcetype       text,
    timestamp          timestamp,
    total              int,
    userdata           map<text, text>
);


