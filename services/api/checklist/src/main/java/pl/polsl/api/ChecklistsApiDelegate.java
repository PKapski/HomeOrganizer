package pl.polsl.api;

import pl.polsl.model.Checklist;
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
     * @see ChecklistsApi#createChecklist
     */
    default ResponseEntity<Void> createChecklist( Checklist  checklist) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ChecklistsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
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
    default ResponseEntity<List<Checklist>> getChecklists( String  creator,
         String  recipent) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {  \"householdId\" : \"testGroup\",  \"creator\" : \"creator\",  \"recipent\" : \"recipent\",  \"itemList\" : [ \"itemList\", \"itemList\" ],  \"id\" : \"id\",  \"title\" : \"title\",  \"creationDate\" : \"2000-01-23\",  \"expirationDate\" : \"2000-01-23\"}, {  \"householdId\" : \"testGroup\",  \"creator\" : \"creator\",  \"recipent\" : \"recipent\",  \"itemList\" : [ \"itemList\", \"itemList\" ],  \"id\" : \"id\",  \"title\" : \"title\",  \"creationDate\" : \"2000-01-23\",  \"expirationDate\" : \"2000-01-23\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
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
     * @see ChecklistsApi#modifyChecklist
     */
    default ResponseEntity<Void> modifyChecklist( String  id,
         Checklist  checklist) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ChecklistsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
