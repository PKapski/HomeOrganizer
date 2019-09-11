package pl.polsl.note;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.api.NotesApiDelegate;
import pl.polsl.model.Note;

import java.util.List;

@Service
public class NoteApiDelegateImpl implements NotesApiDelegate {
    @Override
    public ResponseEntity<Void> createNote(Note note) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteNote(String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Note>> getNotes(String recipent, String creator) {
        System.out.println("halu");
        return null;
    }

    @Override
    public ResponseEntity<Void> modifyNote(String id, Note note) {
        return null;
    }
}
