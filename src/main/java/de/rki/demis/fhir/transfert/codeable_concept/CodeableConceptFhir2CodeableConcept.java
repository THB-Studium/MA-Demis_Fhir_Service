package de.rki.demis.fhir.transfert.codeable_concept;

import de.rki.demis.fhir.model.CodeableConcept;
import de.rki.demis.fhir.transfert.coding.CodingFhir2Coding;
import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CodeableConceptFhir2CodeableConcept {

    @Nullable
    public static CodeableConcept apply(org.hl7.fhir.r4.model.CodeableConcept in) {

        if (Objects.isNull(in)) {
            return null;
        }

        CodeableConcept out = new CodeableConcept();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
//        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
//        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // CodeableConcept type attributes
        out.setCoding(new HashSet<>(CodingFhir2Coding.apply(in.getCoding())));
        out.setText(in.getText());

        return out;
    }

    public static List<CodeableConcept> apply(@NotNull List<org.hl7.fhir.r4.model.CodeableConcept> in) {
        return in.stream().map(CodeableConceptFhir2CodeableConcept::apply).collect(Collectors.toList());
    }

    public static Set<CodeableConcept> apply(@NotNull Set<org.hl7.fhir.r4.model.CodeableConcept> in) {
        return in.stream().map(CodeableConceptFhir2CodeableConcept::apply).collect(Collectors.toSet());
    }
}
