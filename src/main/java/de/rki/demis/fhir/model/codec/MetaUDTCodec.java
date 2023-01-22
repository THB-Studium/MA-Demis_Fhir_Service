//package de.rki.demis.fhir.model.codec;
//
//import java.nio.ByteBuffer;
//import java.util.Objects;
//
//import com.datastax.oss.driver.api.core.ProtocolVersion;
//import com.datastax.oss.driver.api.core.type.DataType;
//import com.datastax.oss.driver.api.core.type.codec.TypeCodec;
//import com.datastax.oss.driver.api.core.type.reflect.GenericType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import de.rki.demis.fhir.model.table.BinaryMod;
//import de.rki.demis.fhir.model.udt.Meta;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//public class MetaUDTCodec implements TypeCodec<Meta> {
//    private static final ObjectMapper toUDTValue = new ObjectMapper();
//    private final TypeCodec<Meta> innerCodec;
//
//    public MetaUDTCodec(@NotNull TypeCodec<Meta> innerCodec, Class<Meta> javaType) {
//        super();
//        this.innerCodec = innerCodec;
//        this.toUDTValue = (BinaryMod)innerCodec.getCqlType();
//    }
//
//
//
//    @Override
//    public Meta parse(String value) {
//        return Objects.isNull(value) ? null : parse(innerCodec.parse(value));
//    }
//
//    @NotNull
//    @Override
//    public GenericType<Meta> getJavaType() {
//        return null;
//    }
//
//    @NotNull
//    @Override
//    public DataType getCqlType() {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public ByteBuffer encode(@Nullable Meta value, @NotNull ProtocolVersion protocolVersion) {
//        return innerCodec.encode(toUDTValue(value), protocolVersion);
//    }
//
//    @Nullable
//    @Override
//    public Meta decode(@Nullable ByteBuffer byteBuffer, @NotNull ProtocolVersion protocolVersion) {
//        return parse(innerCodec.decode(byteBuffer, protocolVersion));
//    }
//
//    @Override
//    public String format(Meta value) {
//        return Objects.isNull(value) ? null : innerCodec.format(toUDTValue(value));
//    }
//
//    protected Meta parse(Meta value) {
//        return Objects.isNull(value) ? null : new Meta(
//                value.getString("street"),
//                value.getInt("zipcode")
//        );
//    }
//
//    protected Meta toUDTValue(Meta value) {
//        return Objects.isNull(value) ? null : toUDTValue.writeValueAsBytes(value));
//                .setString("street", value.getStreet())
//                .setInt("zipcode", value.getZipcode());
//    }
//
//}
//
//
