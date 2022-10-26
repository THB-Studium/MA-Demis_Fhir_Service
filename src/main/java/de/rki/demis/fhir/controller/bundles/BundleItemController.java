package de.rki.demis.fhir.controller.bundles;

//@Validated
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin(origins = ApiConstants.CROSS_ORIGIN_PATH)
//@RequestMapping(value = ApiConstants.BUNDLES_ITEM)
//public class BundleItemController {
//    private static final Logger log = LoggerFactory.getLogger(BundleItemController.class);
//    private final BundleService service;
//
//
//    @RequestMapping(method = RequestMethod.GET)
//    @Produces(MediaType.APPLICATION_JSON)
//    public BundleMod getOne(@PathVariable("bundleId") UUID bundleId) {
//        log.info("::: BundleItemController.getOne() - Get the BundleMod [id={}] :::", bundleId);
//        BundleMod bundleMod = service.getOne(bundleId);
//        log.info("::: BundleItemController.getOne() - BundleMod with [id={}] fetched :::", bundleId);
//
//        return bundleMod;
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void delete(@PathVariable("bundleId") UUID bundleId) {
//        log.info("::: BundleItemController.delete() - Delete the BundleMod [id={}] :::", bundleId);
//        service.delete(bundleId);
//        log.info("::: BundleItemController.delete() - BundleMod with  [id={}] deleted :::", bundleId);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void update(
//            @PathVariable("bundleId") UUID bundleId,
//            @RequestBody @NotBlank String updateString,
//            @RequestHeader(CONTENT_TYPE) org.springframework.http.MediaType mediaType) {
//        log.info("::: BundleItemController.update() - Update the BundleMod [id={}] :::", bundleId);
//        service.update(bundleId, updateString, mediaType);
//        log.info("::: BundleItemController.update() - BundleMod with [id={}] updated :::", bundleId);
//    }
//
//}
