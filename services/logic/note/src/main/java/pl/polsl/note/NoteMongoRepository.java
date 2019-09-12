package pl.polsl.note;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.Note;

import java.util.Optional;

public interface NoteMongoRepository extends MongoRepository<Note, String> {
    Optional<Note> findById(String id);
}
