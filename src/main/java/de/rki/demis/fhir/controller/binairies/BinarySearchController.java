package de.rki.demis.fhir.controller.binairies;

import de.rki.demis.fhir.controller.ApiConstants;
import de.rki.demis.fhir.model.BinaryMod;
import de.rki.demis.fhir.search.criteria.BinaryCriteria;
import de.rki.demis.fhir.service.BinaryService;
import de.rki.demis.fhir.util.DateParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = ApiConstants.CROSS_ORIGIN_PATH)
@RequestMapping(value = ApiConstants.BINARIES_SEARCH)
public class BinarySearchController {
//    private static final Logger log = LoggerFactory.getLogger(BinarySearchController.class);
//    private final BinaryService service;
//
//
//    @Produces(MediaType.APPLICATION_JSON)
//    @RequestMapping(method = RequestMethod.GET)
//    public List<BinaryMod> search(
//            @QueryParam("_tag") String tag,
//            @QueryParam("_lastUpdated") DateParam lastUpdated) {
//
//        log.info("::: BinaryRootController.listAll() - search :::");
//        log.info("::: search Binaries by 'tag' and/or 'lastUpdated' :::");
//
//        BinaryCriteria criteria = new BinaryCriteria();
//
//        criteria.setTag(tag);
//        criteria.setLastUpdated(!Objects.isNull(lastUpdated) ? lastUpdated.getDate() : null);
//
//        return service.search(criteria);
//    }

}
