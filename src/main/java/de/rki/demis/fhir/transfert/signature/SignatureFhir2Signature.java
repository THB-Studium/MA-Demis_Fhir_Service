package de.rki.demis.fhir.transfert.signature;

import de.rki.demis.fhir.model.Signature;
import de.rki.demis.fhir.transfert.code_type.CodeTypeFhir2CodeType;
import de.rki.demis.fhir.transfert.coding.CodingFhir2Coding;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import de.rki.demis.fhir.transfert.reference.ReferenceFhir2Reference;
import de.rki.demis.fhir.transfert.resource.ResourceFhir2Resource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class SignatureFhir2Signature {

    @Nullable
    public static Signature apply(org.hl7.fhir.r4.model.Signature in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Signature out = new Signature();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // Signature type attributes
        out.setType(new HashSet<>(CodingFhir2Coding.apply(in.getType())));
        out.setWhen(in.getWhen());
        out.setWho(ReferenceFhir2Reference.apply(in.getWho()));
        out.setWhoTarget(ResourceFhir2Resource.apply(in.getWhoTarget()));
        out.setOnBehalfOf(ReferenceFhir2Reference.apply(in.getOnBehalfOf()));
        out.setOnBehalfOfTarget(ResourceFhir2Resource.apply(in.getOnBehalfOfTarget()));
        out.setTargetFormat(CodeTypeFhir2CodeType.apply(in.getTargetFormatElement()));
        out.setSigFormat(CodeTypeFhir2CodeType.apply(in.getSigFormatElement()));
        out.setData(in.getData());

        return out;
    }

    public static List<Signature> apply(@NotNull List<org.hl7.fhir.r4.model.Signature> in) {
        return in.stream().map(SignatureFhir2Signature::apply).collect(Collectors.toList());
    }

    public static Set<Signature> apply(@NotNull Set<org.hl7.fhir.r4.model.Signature> in) {
        return in.stream().map(SignatureFhir2Signature::apply).collect(Collectors.toSet());
    }
}
