package de.rki.demis.fhir.transfert.binary;

import de.rki.demis.fhir.model.BinaryMod;
import de.rki.demis.fhir.transfert.meta.MetaFhir2Meta;
import org.hl7.fhir.r4.model.Binary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Binary2BinaryMod {

    @Nullable
    public static BinaryMod apply(Binary in) {

        if (Objects.isNull(in)) {
            return null;
        }

        BinaryMod out = new BinaryMod();

        // Resource type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setResourceType(in.getResourceType().toString());
        out.setMeta(MetaFhir2Meta.apply(in.getMeta()));
//        out.setImplicitRules(UriTypeFhir2UriType.apply(in.getImplicitRulesElement())); todo
//        out.setLanguage(CodeTypeFhir2CodeType.apply(in.getLanguageElement())); todo

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // BinaryMod attributes
        out.setContentType(in.getContentType());
//        out.setSecurityContext(Reference2Reference.apply(in.getSecurityContext())); todo
//        out.setSecurityContextTarget(RessourceFhir2Ressource.apply(in.getSecurityContextTarget())); todo
//        out.setData(Base64BinaryTypeFhir2Base64BinaryType.apply(in.getDataElement())); todo
        out.setData(in.getData());

        return out;
    }

    public static List<BinaryMod> apply(@NotNull List<Binary> in) {
        return in.stream().map(Binary2BinaryMod::apply).collect(Collectors.toList());
    }

    public static Set<BinaryMod> apply(@NotNull Set<Binary> in) {
        return in.stream().map(Binary2BinaryMod::apply).collect(Collectors.toSet());
    }
}