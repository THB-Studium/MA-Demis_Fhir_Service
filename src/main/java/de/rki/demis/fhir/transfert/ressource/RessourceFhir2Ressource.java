package de.rki.demis.fhir.transfert.ressource;

import de.rki.demis.fhir.transfert.meta.MetaFhir2Meta;
import de.rki.demis.fhir.util.fhir_object.classes.Resource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class RessourceFhir2Ressource {

    @Nullable
    public static Resource apply(org.hl7.fhir.r4.model.Resource in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Resource out = new Resource();

        // Resource type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setMeta(MetaFhir2Meta.apply(in.getMeta()));
//        out.setImplicitRules(UriTypeFhir2UriType.apply(in.getImplicitRulesElement()));
//        out.setLanguage(CodeTypeFhir2CodeType.apply(in.getLanguageElement()));

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        return out;
    }

    public static List<Resource> apply(@NotNull List<org.hl7.fhir.r4.model.Resource> in) {
        return in.stream().map(RessourceFhir2Ressource::apply).collect(Collectors.toList());
    }

    public static Set<Resource> apply(@NotNull Set<org.hl7.fhir.r4.model.Resource> in) {
        return in.stream().map(RessourceFhir2Ressource::apply).collect(Collectors.toSet());
    }
}
