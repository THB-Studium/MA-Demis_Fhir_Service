package de.rki.demis.fhir.controller.binairies;

import de.rki.demis.fhir.controller.ApiConstants;
import de.rki.demis.fhir.model.BinaryMod;
import de.rki.demis.fhir.service.BinaryService;
import de.rki.demis.fhir.util.service.FhirParserService;
import lombok.RequiredArgsConstructor;
import org.hl7.fhir.r4.model.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = ApiConstants.CROSS_ORIGIN_PATH)
@RequestMapping(value = ApiConstants.BINARIES_ROOT)
public class BinaryRootController {
    private static final Logger log = LoggerFactory.getLogger(BinaryRootController.class);
    private final BinaryService service;
    private final FhirParserService fhirParserService;
    private final ConversionService conversionService;


    @RequestMapping(method = RequestMethod.POST)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public BinaryMod create(
            @RequestBody @NotBlank String newBinaryString,
            @RequestHeader(CONTENT_TYPE) org.springframework.http.MediaType mediaType) {

        log.info("::: create() - create a BinaryMod :::");
        Binary binary = fhirParserService.parseBinary(newBinaryString, mediaType);
        binary.setId("");
<<<<<<< HEAD
        BinaryMod newBinaryMod = Objects.requireNonNull(conversionService.convert(binary, BinaryMod.class)); // to covert Binary object to BinaryMod object
=======
        BinaryMod newBinaryMod = Objects.requireNonNull(conversionService.convert(binary, BinaryMod.class)); // to covert Bundle object to BundleMod object
>>>>>>> f67cebc (some refactorings done)
        BinaryMod created = service.create(newBinaryMod);
        log.info("::: create() - BinaryMod created :::");

        return created;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public List<BinaryMod> listAll() {
        log.info("::: listAll() - fetch all :::");
        List<BinaryMod> binaries = service.listAll();
        log.info("::: listAll() - Binaries Resources fetched :::");
        return binaries;
    }
}
