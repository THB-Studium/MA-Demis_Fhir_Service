package de.rki.demis.fhir.controller.bundles;

//@Validated
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin(origins = ApiConstants.CROSS_ORIGIN_PATH)
//@RequestMapping(value = ApiConstants.BUNDLES_ROOT)
//public class BundleRootController {
//    private static final Logger log = LoggerFactory.getLogger(BundleRootController.class);
//    private final BundleService service;
//
//
//    @RequestMapping(method = RequestMethod.POST)
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces(MediaType.APPLICATION_JSON)
//    public BundleMod create(
//            @RequestBody @Valid @NotBlank String newBundleString,
//            @RequestHeader(CONTENT_TYPE) org.springframework.http.MediaType mediaType) {
//        log.info("::: BundleRootController.create() - create a Bundle :::");
//        BundleMod created = service.create(newBundleString, mediaType);
//        log.info("::: BundleRootController.create() - Bundle created :::");
//        return created;
//    }
//
//    @Produces(MediaType.APPLICATION_JSON)
//    @RequestMapping(method = RequestMethod.GET)
//    public List<BundleMod> listAll() {
//        log.info("::: BundleRootController.listAll() - fetch all :::");
//        List<BundleMod> bundles = service.listAll();
//        log.info("::: BundleRootController.listAll() - Bundles fetched :::");
//        return bundles;
//    }
//}
