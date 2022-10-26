package de.rki.demis.fhir.controller.patients;

//@RestController
//@CrossOrigin(origins = ApiConstants.CROSS_ORIGIN_PATH)
//@RequestMapping(value = ApiConstants.PATIENTS_ITEM)
//@Validated
//public class PatientItemController {
//    private static final Logger log = LoggerFactory.getLogger(PatientItemController.class);
//    private final PatientService patientService;
//
//    @Autowired
//    public PatientItemController(PatientService patientService) {
//        this.patientService = patientService;
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    @Produces(MediaType.APPLICATION_JSON)
//    public PatientMod getOne(@PathVariable("patientId") String patientId){
//        log.info("::: Get the patient [id={}] :::", patientId);
//        PatientMod patient = patientService.getOne(patientId);
//        log.info("::: PatientMod with [id={}] fetched :::", patientId);
//
//        return patient;
//    }
//
//}
