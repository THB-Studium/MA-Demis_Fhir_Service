package de.rki.demis.fhir.controller.bundles;

import de.rki.demis.fhir.controller.ApiConstants;
import de.rki.demis.fhir.model.table.BundleMod;
import de.rki.demis.fhir.service.BundleService;
import de.rki.demis.fhir.transfert.bundle.Bundle2BundleMod;
import de.rki.demis.fhir.util.service.FhirParserService;
import lombok.RequiredArgsConstructor;
import org.hl7.fhir.r4.model.Bundle;
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
@RequestMapping(value = ApiConstants.BUNDLES_ROOT)
public class BundleRootController {
    private static final Logger log = LoggerFactory.getLogger(BundleRootController.class);
    private final BundleService service;
    private final FhirParserService fhirParserService;
    private final ConversionService conversionService;


    @RequestMapping(method = RequestMethod.POST)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public BundleMod create(
            @RequestBody @NotBlank String newBundleString,
            @RequestHeader(CONTENT_TYPE) org.springframework.http.MediaType mediaType) {

        log.info("::: create() - create a Bundle :::");
        Bundle bundle = fhirParserService.parseBundle(newBundleString, mediaType);
        bundle.setId("");
//        BundleMod newBundleMod = Objects.requireNonNull(conversionService.convert(bundle, BundleMod.class)); // to covert Bundle object to BundleMod object
        BundleMod newBundleMod = Objects.requireNonNull(Bundle2BundleMod.apply(bundle)); // to covert Bundle object to BundleMod object
        BundleMod created = service.create(newBundleMod);
        log.info("::: create() - Bundle created :::");

        return created;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public List<BundleMod> listAll() {
        log.info("::: BundleRootController.listAll() - fetch all :::");
        List<BundleMod> bundles = service.listAll();
        log.info("::: BundleRootController.listAll() - Bundles fetched :::");
        return bundles;
    }
}
