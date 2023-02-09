package de.rki.demis.fhir.util.converter;

import com.datastax.oss.driver.api.core.data.UdtValue;
import com.datastax.oss.driver.internal.core.data.DefaultUdtValue;
import de.rki.demis.fhir.model.udt.Enumeration;
import de.rki.demis.fhir.util.fhir_object.classes.Resource;
import de.rki.demis.fhir.util.fhir_object.enums.BundleType;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

//public class RessourceToUdtValueConverter implements Converter<Resource, UdtValue> {
//
//    @Override
//    public Enumeration<BundleType> convert(@NotNull Enumeration<?> source) {
//        Enumeration<BundleType> out = new Enumeration<BundleType>();
//        out.setId(source.getId());
//        out.setMyStringValue(source.getMyStringValue());
//        out.setExtension(source.getExtension());
//        out.setFormatCommentsPost(source.getFormatCommentsPost());
//        out.setFormatCommentsPre(source.getFormatCommentsPre());
////        out.setMyCoercedValue(source.getMyCoercedValue());
//        out.setUserData(source.getUserData());
//
//        return out;
//    }
//}

//@ReadingConverter
//public class ResourceToUdtValueConverter implements Converter<Resource, UdtValue> {
//
//    @Override
//    public UdtValue convert(Resource source) {
////        UdtValue out = new UdtValue();
//    }
//}

//@WritingConverter
//public class UdtValueToResourceConverter implements Converter<UdtValue, Resource> {
//
//    @Override
//    public Resource convert(UdtValue source) {
//        Resource out = new Resource();
//
//        out.setResourceType(source.getString("resourceType"));
////        out.setMeta(source.getString("meta").);
//
//        return out;
//    }
//}
