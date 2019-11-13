package pl.polsl.api;

import pl.polsl.model.User;
import pl.polsl.model.UsersPaging;
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
 * A delegate to be called by the {@link UsersApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface UsersApiDelegate {

    Logger log = LoggerFactory.getLogger(UsersApi.class);

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
     * @see UsersApi#deleteUser
     */
    default ResponseEntity<Void> deleteUser( String  username) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see UsersApi#getHouseholdUsers
     */
    default ResponseEntity<UsersPaging> getHouseholdUsers( String  householdId,
         String  sortingDirection,
         String  sortedField,
         Integer  firstResult,
         Integer  maxResults) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"maxItems\" : 0,  \"array\" : [ {    \"householdId\" : \"householdId\",    \"firstName\" : \"firstName\",    \"lastName\" : \"lastName\",    \"password\" : \"password\",    \"phoneNumber\" : \"phoneNumber\",    \"id\" : \"id\",    \"email\" : \"email\",    \"username\" : \"username\"  }, {    \"householdId\" : \"householdId\",    \"firstName\" : \"firstName\",    \"lastName\" : \"lastName\",    \"password\" : \"password\",    \"phoneNumber\" : \"phoneNumber\",    \"id\" : \"id\",    \"email\" : \"email\",    \"username\" : \"username\"  } ]}", UsersPaging.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see UsersApi#getUser
     */
    default ResponseEntity<User> getUser( String  username) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"householdId\" : \"householdId\",  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"password\" : \"password\",  \"phoneNumber\" : \"phoneNumber\",  \"id\" : \"id\",  \"email\" : \"email\",  \"username\" : \"username\"}", User.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see UsersApi#modifyUser
     */
    default ResponseEntity<Void> modifyUser( String  username,
         User  user) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see UsersApi#saveUser
     */
    default ResponseEntity<Void> saveUser( User  user) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see UsersApi#setUserHousehold
     */
    default ResponseEntity<Void> setUserHousehold( String  username,
         String  householdId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
