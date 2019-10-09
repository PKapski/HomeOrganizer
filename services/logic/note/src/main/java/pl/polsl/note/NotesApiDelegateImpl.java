package pl.polsl.note;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.api.NotesApiDelegate;
import pl.polsl.model.Note;

import java.util.List;
import java.util.Optional;

@Service
public class NotesApiDelegateImpl implements NotesApiDelegate {

    private final NotesMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    public NotesApiDelegateImpl(NotesMongoRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ResponseEntity<Void> createNote(Note note) {
        repository.save(note);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteNote(String id) {
        Optional<Note> note = repository.findById(id);
        if (note.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.delete(note.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Note>> getNotes(String recipent, String creator) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "creationDate"));
        if (StringUtils.isNotEmpty(recipent))
            query.addCriteria(Criteria.where("recipent").is(recipent));
        if (StringUtils.isNotEmpty(creator))
            query.addCriteria(Criteria.where("creator").is(creator));
        return new ResponseEntity<>(mongoTemplate.find(query, Note.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> modifyNote(String id, Note newNote) {
        Optional<Note> note = repository.findById(id);
        if (note.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newNote.setId(note.get().getId());
        repository.save(newNote);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
