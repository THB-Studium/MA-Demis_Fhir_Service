package de.rki.demis.fhir.controller.bundles;

import de.rki.demis.fhir.controller.ApiConstants;
import de.rki.demis.fhir.model.table.BundleMod;
import de.rki.demis.fhir.service.BundleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = ApiConstants.CROSS_ORIGIN_PATH)
@RequestMapping(value = ApiConstants.BUNDLES_ITEM)
public class BundleItemController {
    private static final Logger log = LoggerFactory.getLogger(BundleItemController.class);
    private final BundleService service;


    @RequestMapping(method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    public BundleMod getOne(@PathVariable("bundleId") UUID bundleId) {
        log.info("::: BundleItemController.getOne() - Get the BundleMod [id={}] :::", bundleId);
        BundleMod bundleMod = service.getOne(bundleId);
        log.info("::: BundleItemController.getOne() - BundleMod with [id={}] fetched :::", bundleId);

        return bundleMod;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@PathVariable("bundleId") UUID bundleId) {
        log.info("::: BundleItemController.delete() - Delete the BundleMod [id={}] :::", bundleId);
        service.delete(bundleId);
        log.info("::: BundleItemController.delete() - BundleMod with  [id={}] deleted :::", bundleId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(
            @PathVariable("bundleId") UUID bundleId,
            @RequestBody @NotBlank String updateString,
            @RequestHeader(CONTENT_TYPE) org.springframework.http.MediaType mediaType) {
        log.info("::: BundleItemController.update() - Update the BundleMod [id={}] :::", bundleId);
        service.update(bundleId, updateString, mediaType);
        log.info("::: BundleItemController.update() - BundleMod with [id={}] updated :::", bundleId);
    }

}
