package de.rki.demis.fhir.controller.bundles;

//@Validated
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin(origins = ApiConstants.CROSS_ORIGIN_PATH)
//@RequestMapping(value = ApiConstants.BUNDLES_SEARCH)
//public class BundleSearchController {
//    private static final Logger log = LoggerFactory.getLogger(BundleSearchController.class);
//    private final BundleService service;
//
//
//    @Produces(MediaType.APPLICATION_JSON)
//    @RequestMapping(method = RequestMethod.GET)
//    public List<BundleMod> search(
//            @QueryParam("_tag") String tag,
//            @QueryParam("_lastUpdated") DateQueryParser lastUpdated) {
//
//        log.info("::: BundleSearchController.search() - search :::");
//        log.info("::: search Binaries by 'tag' and/or 'lastUpdated' :::");
//
//        BundleCriteria criteria = new BundleCriteria();
//
//        criteria.setTag(tag);
//        criteria.setLastUpdated(Objects.nonNull(lastUpdated) ? lastUpdated.getDate() : null);
//
//        return service.search(criteria);
//    }
//
//}
