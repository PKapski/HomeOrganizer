/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package pl.polsl.api;

import pl.polsl.model.Note;
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

@Api(value = "notes", description = "the notes API")
public interface NotesApi {

    NotesApiDelegate getDelegate();

    @ApiOperation(value = "Creates a new note", nickname = "createNote", notes = "", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Counter succesfully created.", response = String.class),
        @ApiResponse(code = 400, message = "Counter couldn't have been created."),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/notes",
        method = RequestMethod.POST)
    default ResponseEntity<String> createNote(@ApiParam(value = "Note to create"  )  @Valid @RequestBody Note note) {
        return getDelegate().createNote(note);
    }


    @ApiOperation(value = "Deletes a note", nickname = "deleteNote", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Note succesfully deleted."),
        @ApiResponse(code = 400, message = "Note couldn't have been deleted."),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/notes/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteNote(@ApiParam(value = "Id of note to delete",required=true) @PathVariable("id") String id) {
        return getDelegate().deleteNote(id);
    }


    @ApiOperation(value = "Gets notes", nickname = "getNotes", notes = "Return list of notes", response = Note.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of notes", response = Note.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/notes",
        method = RequestMethod.GET)
    default ResponseEntity<List<Note>> getNotes(@ApiParam(value = "") @Valid @RequestParam(value = "recipent", required = false) String recipent,@ApiParam(value = "") @Valid @RequestParam(value = "creator", required = false) String creator) {
        return getDelegate().getNotes(recipent, creator);
    }


    @ApiOperation(value = "Modifies a note", nickname = "modifyNote", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Note succesfully modified."),
        @ApiResponse(code = 400, message = "Note couldn't have been modified."),
        @ApiResponse(code = 500, message = "An unexpected error occured.", response = Object.class) })
    @RequestMapping(value = "/notes/{id}",
        method = RequestMethod.PATCH)
    default ResponseEntity<Void> modifyNote(@ApiParam(value = "Id of note to delete",required=true) @PathVariable("id") String id,@ApiParam(value = "Modified note"  )  @Valid @RequestBody Note note) {
        return getDelegate().modifyNote(id, note);
    }

}
