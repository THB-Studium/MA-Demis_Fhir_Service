package de.rki.demis.fhir.transfert.bundle;

import de.rki.demis.fhir.model.BundleMod;
import org.hl7.fhir.r4.model.Bundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Bundle2BundleMod {

    @Nullable
    public static BundleMod apply(Bundle in) {
        if (Objects.isNull(in)) {
            return null;
        }

        BundleMod out = new BundleMod();

        out.setId(Objects.nonNull(in.getId()) ? UUID.fromString(in.getId()) : null);

        return out;
    }

    public static List<BundleMod> apply(@NotNull List<Bundle> in) {
        return in.stream().map(Bundle2BundleMod::apply).collect(Collectors.toList());
    }

    public static Set<BundleMod> apply(@NotNull Set<Bundle> in) {
        return in.stream().map(Bundle2BundleMod::apply).collect(Collectors.toSet());
    }
}
