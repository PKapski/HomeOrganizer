package pl.polsl.note;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.api.NotesApiDelegate;
import pl.polsl.model.Note;
import pl.polsl.model.NotesPaging;

@Service
public class NotesApiDelegateImpl implements NotesApiDelegate {

    private final NotesMongoRepository repository;
    private final NotesService service;

    public NotesApiDelegateImpl(NotesMongoRepository repository, NotesService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public ResponseEntity<String> saveNote(Note note) {
        String noteId = service.saveNote(note);
        return new ResponseEntity<>(noteId, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteNote(String id) {
        if (service.deleteNote(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<NotesPaging> getNotes(String username, String householdId, String sortingDirection, String sortedField, Integer  firstResult, Integer  maxResults) {
        NotesPaging notePaging = service.getFilteredNotesPaging(username,householdId,sortingDirection,sortedField, firstResult, maxResults);
        return new ResponseEntity<>(notePaging, HttpStatus.OK);
    }
}
