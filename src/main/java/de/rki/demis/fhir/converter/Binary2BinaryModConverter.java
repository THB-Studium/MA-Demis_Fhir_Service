package de.rki.demis.fhir.converter;

import de.rki.demis.fhir.model.BinaryMod;
import de.rki.demis.fhir.transfert.code_type.CodeTypeFhir2CodeType;
import de.rki.demis.fhir.transfert.meta.MetaFhir2Meta;
import de.rki.demis.fhir.transfert.reference.ReferenceFhir2Reference;
import de.rki.demis.fhir.transfert.resource.ResourceFhir2Resource;
import de.rki.demis.fhir.transfert.uri_type.UriTypeFhir2UriType;
import org.hl7.fhir.r4.model.Binary;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

public class Binary2BinaryModConverter implements Converter<Binary, BinaryMod> {

    @Override
    public BinaryMod convert(@NotNull Binary in) {
        BinaryMod out = new BinaryMod();

        // Resource type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setResourceType(in.getResourceType().toString());
        out.setMeta(MetaFhir2Meta.apply(in.getMeta()));
        out.setImplicitRules(UriTypeFhir2UriType.apply(in.getImplicitRulesElement()));
        out.setLanguage(CodeTypeFhir2CodeType.apply(in.getLanguageElement()));

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // BinaryMod attributes
        out.setContentType(in.getContentType());
        out.setSecurityContext(ReferenceFhir2Reference.apply(in.getSecurityContext()));
        out.setSecurityContextTarget(ResourceFhir2Resource.apply(in.getSecurityContextTarget()));
//        out.setData(Base64BinaryTypeFhir2Base64BinaryType.apply(in.getDataElement())); todo
        out.setData(in.getData());

        return out;
    }
}
