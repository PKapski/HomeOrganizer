package pl.polsl.api;

import pl.polsl.model.Checklist;
import pl.polsl.model.ChecklistsPaging;
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
 * A delegate to be called by the {@link ChecklistsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface ChecklistsApiDelegate {

    Logger log = LoggerFactory.getLogger(ChecklistsApi.class);

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
     * @see ChecklistsApi#deleteChecklist
     */
    default ResponseEntity<Void> deleteChecklist( String  id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ChecklistsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see ChecklistsApi#getChecklists
     */
    default ResponseEntity<ChecklistsPaging> getChecklists( String  username,
         String  householdId,
         String  sortingDirection,
         String  sortedField,
         Integer  firstResult,
         Integer  maxResults) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"maxItems\" : 0,  \"array\" : [ {    \"householdId\" : \"testHousehold\",    \"creator\" : \"creator\",    \"recipent\" : \"recipent\",    \"visibleToEveryone\" : true,    \"itemList\" : [ {      \"message\" : \"message\",      \"isChecked\" : false    }, {      \"message\" : \"message\",      \"isChecked\" : false    } ],    \"id\" : \"id\",    \"title\" : \"title\",    \"creationDate\" : \"2000-01-23\",    \"expirationDate\" : \"2000-01-23\"  }, {    \"householdId\" : \"testHousehold\",    \"creator\" : \"creator\",    \"recipent\" : \"recipent\",    \"visibleToEveryone\" : true,    \"itemList\" : [ {      \"message\" : \"message\",      \"isChecked\" : false    }, {      \"message\" : \"message\",      \"isChecked\" : false    } ],    \"id\" : \"id\",    \"title\" : \"title\",    \"creationDate\" : \"2000-01-23\",    \"expirationDate\" : \"2000-01-23\"  } ]}", ChecklistsPaging.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ChecklistsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see ChecklistsApi#saveChecklist
     */
    default ResponseEntity<String> saveChecklist( Checklist  checklist) {
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
            log.warn("ObjectMapper or HttpServletRequest not configured in default ChecklistsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
