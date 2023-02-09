package de.rki.demis.fhir.transfert.element;

import de.rki.demis.fhir.transfert.extension.ExtensionFhir2Extension;
import de.rki.demis.fhir.util.fhir_object.classes.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ElementFhir2Element {

    @Nullable
    public static Element apply(org.hl7.fhir.r4.model.Element in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Element out = new Element();

        // Element type attributes
        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);
        out.setExtension(ExtensionFhir2Extension.apply(new HashSet<>(in.getExtension())));
        out.setDisallowExtensions(in.getExtensionFirstRep().isDisallowExtensions());

        // Base type attributes
        out.setFormatCommentsPre(new HashSet<>(in.getFormatCommentsPre()));
        out.setFormatCommentsPost(new HashSet<>(in.getFormatCommentsPost()));

        return out;
    }

    public static List<Element> apply(@NotNull List<org.hl7.fhir.r4.model.Element> in) {
        return in.stream().map(ElementFhir2Element::apply).collect(Collectors.toList());
    }

    public static Set<Element> apply(@NotNull Set<org.hl7.fhir.r4.model.Element> in) {
        return in.stream().map(ElementFhir2Element::apply).collect(Collectors.toSet());
    }
}
