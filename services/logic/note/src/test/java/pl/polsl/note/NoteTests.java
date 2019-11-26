package pl.polsl.note;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import pl.polsl.model.Note;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
public class NoteTests {

    @Autowired
    private NotesMongoRepository repository;

    @Commit
    @Test
    public void shouldSaveNoteAndReturnId(){
        Note note = null;
        Mockito.when(repository.save(any(Note.class))).then(arg->arg.getArgument(0));
        assertNotNull(repository.save(note));
    }
}
