/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package pl.polsl.api;

import pl.polsl.model.Household;
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

@Api(value = "households", description = "the households API")
public interface HouseholdsApi {

    HouseholdsApiDelegate getDelegate();

    @ApiOperation(value = "Deletes a household", nickname = "deleteHousehold", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Household succesfully deleted."),
        @ApiResponse(code = 400, message = "Household couldn't have been deleted."),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/households/{householdId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteHousehold(@ApiParam(value = "",required=true) @PathVariable("householdId") String householdId) {
        return getDelegate().deleteHousehold(householdId);
    }


    @ApiOperation(value = "Gets household information", nickname = "getHousehold", notes = "Returns household", response = Household.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Household.class),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/households/{householdId}",
        method = RequestMethod.GET)
    default ResponseEntity<Household> getHousehold(@ApiParam(value = "",required=true) @PathVariable("householdId") String householdId) {
        return getDelegate().getHousehold(householdId);
    }


    @ApiOperation(value = "Modifies the household", nickname = "modifyHousehold", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Household succesfully modified."),
        @ApiResponse(code = 400, message = "Household couldn't have been modified."),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/households/{householdId}",
        method = RequestMethod.PATCH)
    default ResponseEntity<Void> modifyHousehold(@ApiParam(value = "",required=true) @PathVariable("householdId") String householdId,@ApiParam(value = "Modified household"  )  @Valid @RequestBody Household household) {
        return getDelegate().modifyHousehold(householdId, household);
    }


    @ApiOperation(value = "Creates a new household or patches existing", nickname = "saveHousehold", notes = "New household", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Household succesfully saved.", response = String.class),
        @ApiResponse(code = 400, message = "Household couldn't have been created."),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/households",
        method = RequestMethod.POST)
    default ResponseEntity<String> saveHousehold(@ApiParam(value = "Household to create"  )  @Valid @RequestBody Household household) {
        return getDelegate().saveHousehold(household);
    }

}
