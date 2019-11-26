package pl.polsl.app;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.model.Note;
import pl.polsl.model.NotesPaging;
import pl.polsl.note.NotesService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteTests {

    private static final String TEST_USER = "Test";
    private static final String TEST_TITLE = "Test title";
    private static final String TEST_MESSAGE = "Test message";

    @Autowired
    private NotesService service;

    @Test
    public void shouldSaveNoteToDBAndReturnId() {
        Note note = getNote();
        Assertions.assertNotNull(service.saveNote(note));
    }

    @Test
    public void shouldReturnListOfNotesFromDB() {
        service.saveNote(getNote());
        NotesPaging notes = service.getFilteredNotesPaging(TEST_USER, null, null, null, null, null);
        Assertions.assertTrue(notes.getMaxItems() > 0, "Notes maxItems wrongly evaluated!");
        Assertions.assertTrue(notes.getArray().size() > 0, "Notes list is empty!");
    }

    @Test
    public void shouldDeleteNoteFromDBAndReturnTrue(){
        String id = service.saveNote(getNote());
        Assertions.assertTrue(service.deleteNote(id));
    }

    private Note getNote() {
        Note note = new Note();
        note.setTitle(TEST_TITLE);
        note.setMessage(TEST_MESSAGE);
        note.setCreator(TEST_USER);
        return note;
    }
}
