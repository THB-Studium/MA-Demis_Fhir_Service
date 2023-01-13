package de.rki.demis.fhir.transfert.code_type;

import de.rki.demis.fhir.model.CodeType;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CodeTypeFhir2CodeType {

    @Nullable
    public static CodeType apply(org.hl7.fhir.r4.model.CodeType in) {

        if (Objects.isNull(in)) {
            return null;
        }

        CodeType out = new CodeType();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
//        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // PrimitiveType attributes
        out.setMyStringValue(in.getValueAsString());
//        out.setMyCoercedValue(in.getValue());

        // CodeType attributes
        out.setSystem(in.getSystem());

        return out;
    }

    public static List<CodeType> apply(@NotNull List<org.hl7.fhir.r4.model.CodeType> in) {
        return in.stream().map(CodeTypeFhir2CodeType::apply).collect(Collectors.toList());
    }

    public static Set<CodeType> apply(@NotNull Set<org.hl7.fhir.r4.model.CodeType> in) {
        return in.stream().map(CodeTypeFhir2CodeType::apply).collect(Collectors.toSet());
    }
}
