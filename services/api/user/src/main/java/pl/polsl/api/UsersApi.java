/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package pl.polsl.api;

import pl.polsl.model.User;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Api(value = "users", description = "the users API")
public interface UsersApi {

    UsersApiDelegate getDelegate();

    @ApiOperation(value = "Creates a new user", nickname = "createUser", notes = "New user", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "User succesfully created."),
        @ApiResponse(code = 400, message = "User couldn't have been created."),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/users",
        method = RequestMethod.POST)
    default ResponseEntity<Void> createUser(@ApiParam(value = "User to create"  )  @Valid @RequestBody User user) {
        return getDelegate().createUser(user);
    }


    @ApiOperation(value = "Deletes a user", nickname = "deleteUser", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "User succesfully deleted."),
        @ApiResponse(code = 400, message = "User couldn't have been deleted."),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/users/{username}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteUser(@ApiParam(value = "",required=true) @PathVariable("username") String username) {
        return getDelegate().deleteUser(username);
    }


    @ApiOperation(value = "Gets users belonging to given household", nickname = "getHouseholdUsers", notes = "", response = User.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of household users", response = User.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/users/household/{householdId}",
        method = RequestMethod.GET)
    default ResponseEntity<List<User>> getHouseholdUsers(@ApiParam(value = "",required=true) @PathVariable("householdId") String householdId) {
        return getDelegate().getHouseholdUsers(householdId);
    }


    @ApiOperation(value = "Gets user information", nickname = "getUser", notes = "Returns user", response = User.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = User.class),
        @ApiResponse(code = 404, message = "User not found"),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/users/{username}",
        method = RequestMethod.GET)
    default ResponseEntity<User> getUser(@ApiParam(value = "",required=true) @PathVariable("username") String username) {
        return getDelegate().getUser(username);
    }


    @ApiOperation(value = "Modifies the user", nickname = "modifyUser", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "User succesfully modified."),
        @ApiResponse(code = 400, message = "User couldn't have been modified."),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/users/{username}",
        method = RequestMethod.PATCH)
    default ResponseEntity<Void> modifyUser(@ApiParam(value = "",required=true) @PathVariable("username") String username,@ApiParam(value = "Modified user"  )  @Valid @RequestBody User user) {
        return getDelegate().modifyUser(username, user);
    }

}