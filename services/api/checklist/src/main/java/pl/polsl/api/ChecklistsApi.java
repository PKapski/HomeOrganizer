/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package pl.polsl.api;

import pl.polsl.model.Checklist;
import pl.polsl.model.ChecklistsPaging;
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

@Api(value = "checklists", description = "the checklists API")
public interface ChecklistsApi {

    ChecklistsApiDelegate getDelegate();

    @ApiOperation(value = "Deletes a checklist", nickname = "deleteChecklist", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Checklist succesfully deleted."),
        @ApiResponse(code = 400, message = "Checklist couldn't have been deleted."),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/checklists/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteChecklist(@ApiParam(value = "Id of checklist to delete",required=true) @PathVariable("id") String id) {
        return getDelegate().deleteChecklist(id);
    }


    @ApiOperation(value = "Get all checklists", nickname = "getChecklists", notes = "Return list of checklists", response = ChecklistsPaging.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of checklists with paging", response = ChecklistsPaging.class),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/checklists",
        method = RequestMethod.GET)
    default ResponseEntity<ChecklistsPaging> getChecklists(@ApiParam(value = "") @Valid @RequestParam(value = "username", required = false) String username,@ApiParam(value = "") @Valid @RequestParam(value = "householdId", required = false) String householdId,@ApiParam(value = "", allowableValues = "ASC, DESC") @Valid @RequestParam(value = "sortingDirection", required = false) String sortingDirection,@ApiParam(value = "") @Valid @RequestParam(value = "sortedField", required = false) String sortedField,@ApiParam(value = "") @Valid @RequestParam(value = "firstResult", required = false) Integer firstResult,@ApiParam(value = "") @Valid @RequestParam(value = "maxResults", required = false) Integer maxResults) {
        return getDelegate().getChecklists(username, householdId, sortingDirection, sortedField, firstResult, maxResults);
    }


    @ApiOperation(value = "Creates or modifies checklist if it already exists", nickname = "saveChecklist", notes = "", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Checklist succesfully created.", response = String.class),
        @ApiResponse(code = 400, message = "Checklist couldn't have been saved."),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/checklists",
        method = RequestMethod.POST)
    default ResponseEntity<String> saveChecklist(@ApiParam(value = "checklist to save"  )  @Valid @RequestBody Checklist checklist) {
        return getDelegate().saveChecklist(checklist);
    }

}
