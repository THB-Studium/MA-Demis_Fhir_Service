package de.rki.demis.fhir.transfert.binary;

import de.rki.demis.fhir.model.BinaryMod;
import org.hl7.fhir.r4.model.Binary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Binary2BinaryMod {

    @Nullable
    public static BinaryMod apply(Binary in) {

        if (Objects.isNull(in)) {
            return null;
        }

        BinaryMod out = new BinaryMod();

//        out.setId(Objects.isNull(in.getId()) ? null : UUID.fromString(in.getId()));
//        out.setMeta(new Meta(null,
//                in.getMeta().getVersionId(),
//                in.getMeta().getLastUpdated(),
//                in.getMeta().getSource(),
//                in.getMeta().getProfile().toString(),
//                null,
//                null));
//        out.setContentType(in.getContentType());
//        out.setData(in.getData().toString());

        return out;
    }

    public static List<BinaryMod> apply(@NotNull List<Binary> in) {
        return in.stream().map(Binary2BinaryMod::apply).collect(Collectors.toList());
    }

    public static Set<BinaryMod> apply(@NotNull Set<Binary> in) {
        return in.stream().map(Binary2BinaryMod::apply).collect(Collectors.toSet());
    }
}
