//package de.rki.demis.fhir.controller.binairies;
//
//import de.rki.demis.fhir.controller.ApiConstants;
//import de.rki.demis.fhir.model.BinaryMod;
//import de.rki.demis.fhir.service.BinaryService;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.constraints.NotBlank;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.util.UUID;
//
//import static de.rki.demis.fhir.util.constant.Constants.BINARY_ID;
//import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
//
//@Validated
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin(origins = ApiConstants.CROSS_ORIGIN_PATH)
//@RequestMapping(value = ApiConstants.BINARIES_ITEM)
//public class BinaryItemController {
//    private static final Logger log = LoggerFactory.getLogger(BinaryItemController.class);
//    private final BinaryService service;
//
//
//    @RequestMapping(method = RequestMethod.GET)
//    @Produces(MediaType.APPLICATION_JSON)
//    public BinaryMod getOne(@PathVariable(BINARY_ID) UUID binaryId) {
//        log.info("::: BinaryItemController.getOne() - Get the BinaryMod [id={}] :::", binaryId);
//        BinaryMod binaryObj = service.getOne(binaryId);
//        log.info("::: BinaryItemController.getOne() - BinaryMod with [id={}] fetched :::", binaryId);
//
//        return binaryObj;
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void delete(@PathVariable(BINARY_ID) UUID binaryId) {
//        log.info("::: BinaryItemController.delete() - Delete the BinaryMod [id={}] :::", binaryId);
//        service.delete(binaryId);
//        log.info("::: BinaryItemController.delete() - BinaryMod with  [id={}] deleted :::", binaryId);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void update(
//            @PathVariable(BINARY_ID) UUID binaryId,
//            @RequestBody @NotBlank String updateString,
//            @RequestHeader(CONTENT_TYPE) org.springframework.http.MediaType mediaType) {
//        log.info("::: BinaryItemController.update() - Update the BinaryMod [id={}] :::", binaryId);
//        service.update(binaryId, updateString, mediaType);
//        log.info("::: BinaryItemController.update() - BinaryMod with [id={}] updated :::", binaryId);
//    }
//
//}
