package de.rki.demis.fhir.transfert.coding;

import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.transfert.code_type.CodeTypeFhir2CodeType;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import de.rki.demis.fhir.transfert.uri_type.UriTypeFhir2UriType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CodingFhir2Coding {

    @Nullable
    public static Coding apply(org.hl7.fhir.r4.model.Coding in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Coding out = new Coding();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // Coding type attributes
        out.setSystem(UriTypeFhir2UriType.apply(in.getSystemElement()));
        out.setVersion(in.getVersion());
        out.setCode(CodeTypeFhir2CodeType.apply(in.getCodeElement()));
        out.setDisplay(in.getDisplay());
        out.setUserSelected(in.getUserSelected());

        return out;
    }

    public static List<Coding> apply(@NotNull List<org.hl7.fhir.r4.model.Coding> in) {
        return in.stream().map(CodingFhir2Coding::apply).collect(Collectors.toList());
    }

    public static Set<Coding> apply(@NotNull Set<org.hl7.fhir.r4.model.Coding> in) {
        return in.stream().map(CodingFhir2Coding::apply).collect(Collectors.toSet());
    }
}
