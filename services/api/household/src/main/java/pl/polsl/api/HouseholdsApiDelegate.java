package pl.polsl.api;

import pl.polsl.model.Household;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * A delegate to be called by the {@link HouseholdsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface HouseholdsApiDelegate {

    Logger log = LoggerFactory.getLogger(HouseholdsApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    /**
     * @see HouseholdsApi#deleteHousehold
     */
    default ResponseEntity<Void> deleteHousehold( String  householdId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default HouseholdsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see HouseholdsApi#getHousehold
     */
    default ResponseEntity<Household> getHousehold( String  householdId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"image\" : \"\",  \"name\" : \"name\",  \"description\" : \"description\",  \"id\" : \"id\"}", Household.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default HouseholdsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see HouseholdsApi#modifyHousehold
     */
    default ResponseEntity<Void> modifyHousehold( String  householdId,
         Household  household) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default HouseholdsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see HouseholdsApi#saveHousehold
     */
    default ResponseEntity<String> saveHousehold( Household  household) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("\"\"", String.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default HouseholdsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
