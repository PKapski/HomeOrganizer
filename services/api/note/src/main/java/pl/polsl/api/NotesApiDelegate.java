package pl.polsl.api;

import pl.polsl.model.Note;
import pl.polsl.model.NotesPaging;
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
 * A delegate to be called by the {@link NotesApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface NotesApiDelegate {

    Logger log = LoggerFactory.getLogger(NotesApi.class);

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
     * @see NotesApi#deleteNote
     */
    default ResponseEntity<Void> deleteNote( String  id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default NotesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see NotesApi#getNotes
     */
    default ResponseEntity<NotesPaging> getNotes( String  username,
         String  householdId,
         String  sortingDirection,
         String  sortedField,
         Integer  firstResult,
         Integer  maxResults) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"maxItems\" : 0,  \"array\" : [ {    \"householdId\" : \"testHousehold\",    \"creator\" : \"creator\",    \"recipent\" : \"recipent\",    \"visibleToEveryone\" : true,    \"id\" : \"id\",    \"title\" : \"title\",    \"message\" : \"message\",    \"expirationDate\" : \"2000-01-23\"  }, {    \"householdId\" : \"testHousehold\",    \"creator\" : \"creator\",    \"recipent\" : \"recipent\",    \"visibleToEveryone\" : true,    \"id\" : \"id\",    \"title\" : \"title\",    \"message\" : \"message\",    \"expirationDate\" : \"2000-01-23\"  } ]}", NotesPaging.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default NotesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see NotesApi#saveNote
     */
    default ResponseEntity<String> saveNote( Note  note) {
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
            log.warn("ObjectMapper or HttpServletRequest not configured in default NotesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
