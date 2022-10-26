package de.rki.demis.fhir.transfert.meta;

import de.rki.demis.fhir.model.Meta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MetaFhir2Meta {

    @Nullable
    public static Meta apply(org.hl7.fhir.r4.model.Meta in) {

        if (Objects.isNull(in)) {
            return null;
        }

        Meta out = new Meta();

//        out.setId(!Objects.isNull(in.getId()) ? UUID.fromString(in.getId()) : null);
//        out.setVersionId(in.getVersionId());
//        out.setTag(in.getTag().stream().collect(Collectors.toSet()));

        return out;
    }

    public static List<Meta> apply(@NotNull List<org.hl7.fhir.r4.model.Meta> in) {
        return in.stream().map(MetaFhir2Meta::apply).collect(Collectors.toList());
    }

    public static Set<Meta> apply(@NotNull Set<org.hl7.fhir.r4.model.Meta> in) {
        return in.stream().map(MetaFhir2Meta::apply).collect(Collectors.toSet());
    }
}
