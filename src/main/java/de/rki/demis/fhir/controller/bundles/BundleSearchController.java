package de.rki.demis.fhir.controller.bundles;

import de.rki.demis.fhir.controller.ApiConstants;
import de.rki.demis.fhir.model.BundleMod;
import de.rki.demis.fhir.search.criteria.BundleCriteria;
import de.rki.demis.fhir.service.BundleService;
import de.rki.demis.fhir.util.parser.DateQueryParser;
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

import static de.rki.demis.fhir.util.constant.Constants.LAST_UPDATED;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = ApiConstants.CROSS_ORIGIN_PATH)
@RequestMapping(value = ApiConstants.BUNDLES_SEARCH)
public class BundleSearchController {
    private static final Logger log = LoggerFactory.getLogger(BundleSearchController.class);
    private final BundleService service;


    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public List<BundleMod> search(
            @QueryParam(LAST_UPDATED) String _lastUpdated) {
        log.info("::: Bundle - search() :::");
        log.info(String.format("::: Queries: 'lastUpdated=%S' :::", _lastUpdated));

        BundleCriteria criteria = new BundleCriteria();
        DateQueryParser dateQueryParser = new DateQueryParser(_lastUpdated);

        // lastUpdated
        criteria.setSearchDateOp(dateQueryParser.getPrefix());
        criteria.setLastUpdated(dateQueryParser.getDate());

        return service.search(criteria);
    }

}
