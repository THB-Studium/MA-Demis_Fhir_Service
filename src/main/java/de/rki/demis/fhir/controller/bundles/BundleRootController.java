package de.rki.demis.fhir.controller.bundles;

import de.rki.demis.fhir.controller.ApiConstants;
import de.rki.demis.fhir.model.BundleMod;
import de.rki.demis.fhir.service.BundleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = ApiConstants.CROSS_ORIGIN_PATH)
@RequestMapping(value = ApiConstants.BUNDLES_ROOT)
public class BundleRootController {
    private static final Logger log = LoggerFactory.getLogger(BundleRootController.class);
    private final BundleService service;


    @RequestMapping(method = RequestMethod.POST)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public BundleMod create(
            @RequestBody @NotBlank String newBundleString,
            @RequestHeader(CONTENT_TYPE) org.springframework.http.MediaType mediaType) {
        log.info("::: BundleRootController.create() - create a Bundle :::");
        BundleMod created = service.create(newBundleString, mediaType);
        log.info("::: BundleRootController.create() - Bundle created :::");
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
