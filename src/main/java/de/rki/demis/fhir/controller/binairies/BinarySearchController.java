package de.rki.demis.fhir.controller.binairies;

import de.rki.demis.fhir.controller.ApiConstants;
import de.rki.demis.fhir.model.BinaryMod;
import de.rki.demis.fhir.search.criteria.BinaryCriteria;
import de.rki.demis.fhir.service.model.BinaryService;
import de.rki.demis.fhir.util.DateQueryParser;
import de.rki.demis.fhir.util.TagQueryParser;
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
import static de.rki.demis.fhir.util.constant.Constants.TAG;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = ApiConstants.CROSS_ORIGIN_PATH)
@RequestMapping(value = ApiConstants.BINARIES_SEARCH)
public class BinarySearchController {
    private static final Logger log = LoggerFactory.getLogger(BinarySearchController.class);
    private final BinaryService service;


    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public List<BinaryMod> search(
            @QueryParam(TAG) String _tag,
            @QueryParam(LAST_UPDATED) String _lastUpdated) {
        log.info("::: Binary - search() :::");
        log.info(String.format("::: Queries: 'tag=%s', 'lastUpdated=%S' :::", _tag, _lastUpdated));

        BinaryCriteria criteria         = new BinaryCriteria();
        TagQueryParser tagQueryParser   = new TagQueryParser(_tag);
        DateQueryParser dateQueryParser = new DateQueryParser(_lastUpdated);

        // tag
        criteria.setSystem(tagQueryParser.getSystem());
        criteria.setCode(tagQueryParser.getCode());
        criteria.setDisplay(tagQueryParser.getDisplay());

        // lastUpdated
        criteria.setLastUpdatedOp(dateQueryParser.getPrefix());
        criteria.setLastUpdated(dateQueryParser.getDate());

        return service.search(criteria);
    }

}
