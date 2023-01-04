package de.rki.demis.fhir.transfert.identifier;

import de.rki.demis.fhir.model.Identifier;
import de.rki.demis.fhir.transfert.codeable_concept.CodeableConceptFhir2CodeableConcept;
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

public class IdentifierFhir2Identifier {

    @Nullable
    public static Identifier apply(org.hl7.fhir.r4.model.Identifier in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Identifier out = new Identifier();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        // Identifier type attributes
//        out.setUse(null); // todo
        out.setType(CodeableConceptFhir2CodeableConcept.apply(in.getType()));
        out.setSystem(UriTypeFhir2UriType.apply(in.getSystemElement()));
        out.setValue(in.getValue());
        out.setPeriod(null); // todo
        out.setAssigner(null); // todo: has benn set to null to avoid while wit Reference

        return out;
    }

    public static List<Identifier> apply(@NotNull List<org.hl7.fhir.r4.model.Identifier> in) {
        return in.stream().map(IdentifierFhir2Identifier::apply).collect(Collectors.toList());
    }

    public static Set<Identifier> apply(@NotNull Set<org.hl7.fhir.r4.model.Identifier> in) {
        return in.stream().map(IdentifierFhir2Identifier::apply).collect(Collectors.toSet());
    }
}
