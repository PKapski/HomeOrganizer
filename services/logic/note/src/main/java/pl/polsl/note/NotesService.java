package pl.polsl.note;

import org.springframework.stereotype.Service;
import pl.polsl.model.Note;

@Service
public class NotesService {

    private final NotesMongoRepository repository;

    public NotesService(NotesMongoRepository repository) {
        this.repository = repository;
    }

    String saveNote(Note note){
        return repository.save(note).getId();
    }
}
