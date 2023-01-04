package de.rki.demis.fhir.transfert.resource;

import de.rki.demis.fhir.model.Resource;
import de.rki.demis.fhir.transfert.code_type.CodeTypeFhir2CodeType;
import de.rki.demis.fhir.transfert.meta.MetaFhir2Meta;
import de.rki.demis.fhir.transfert.uri_type.UriTypeFhir2UriType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static de.rki.demis.fhir.util.constant.Constants.UUID_REGEX;

public class ResourceFhir2Resource {

    @Nullable
    public static Resource apply(org.hl7.fhir.r4.model.Resource in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Resource out = new Resource();


        // Resource type attributes
        out.setId(Objects.nonNull(in.getId())
                ? UUID_REGEX.matcher(in.getId()).find()
                    ? UUID.fromString(in.getId())
                    : null // todo: handle this null
                : null);
        out.setMeta(MetaFhir2Meta.apply(in.getMeta()));
        out.setImplicitRules(UriTypeFhir2UriType.apply(in.getImplicitRulesElement()));
        out.setLanguage(CodeTypeFhir2CodeType.apply(in.getLanguageElement()));

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        return out;
    }

    public static List<Resource> apply(@NotNull List<org.hl7.fhir.r4.model.Resource> in) {
        return in.stream().map(ResourceFhir2Resource::apply).collect(Collectors.toList());
    }

    public static Set<Resource> apply(@NotNull Set<org.hl7.fhir.r4.model.Resource> in) {
        return in.stream().map(ResourceFhir2Resource::apply).collect(Collectors.toSet());
    }
}
