package pl.polsl.api;

import pl.polsl.model.CalendarEvent;
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
 * A delegate to be called by the {@link CalendarApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface CalendarApiDelegate {

    Logger log = LoggerFactory.getLogger(CalendarApi.class);

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
     * @see CalendarApi#deleteCalendarEvent
     */
    default ResponseEntity<Void> deleteCalendarEvent( String  id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default CalendarApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see CalendarApi#getCalendarEvents
     */
    default ResponseEntity<List<CalendarEvent>> getCalendarEvents( String  householdId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {  \"householdId\" : \"householdId\",  \"allDay\" : true,  \"color\" : {    \"secondary\" : \"secondary\",    \"primary\" : \"primary\"  },  \"draggable\" : true,  \"resizable\" : {    \"beforeStart\" : true,    \"afterEnd\" : true  },  \"start\" : \"2000-01-23T04:56:07.000+00:00\",  \"end\" : \"2000-01-23T04:56:07.000+00:00\",  \"id\" : \"id\",  \"title\" : \"title\"}, {  \"householdId\" : \"householdId\",  \"allDay\" : true,  \"color\" : {    \"secondary\" : \"secondary\",    \"primary\" : \"primary\"  },  \"draggable\" : true,  \"resizable\" : {    \"beforeStart\" : true,    \"afterEnd\" : true  },  \"start\" : \"2000-01-23T04:56:07.000+00:00\",  \"end\" : \"2000-01-23T04:56:07.000+00:00\",  \"id\" : \"id\",  \"title\" : \"title\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default CalendarApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see CalendarApi#saveCalendarEvent
     */
    default ResponseEntity<String> saveCalendarEvent( String  householdId,
         CalendarEvent  event) {
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
            log.warn("ObjectMapper or HttpServletRequest not configured in default CalendarApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
