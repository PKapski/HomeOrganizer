package pl.polsl.note;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.polsl.model.Note;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    private final NotesMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    public NotesService(NotesMongoRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    String saveNote(Note note) {
        return repository.save(note).getId();
    }

    boolean deleteNote(String id) {
        Optional<Note> note = repository.findById(id);
        if (note.isPresent()) {
            repository.delete(note.get());
            return true;
        }
        return false;
    }

    List<Note> getFilteredNotes(String username, String householdId, String sortingDirection) {
        Query query = new Query();
        if (StringUtils.isNotEmpty(username)) {
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("recipent").is(username), Criteria.where("visibleToEveryone").is(true), Criteria.where("creator").is(username));
            query.addCriteria(criteria);
        }
        if (StringUtils.isNotEmpty(householdId)) {
            query.addCriteria(Criteria.where("householdId").is(householdId));
        }
        if (StringUtils.isNotEmpty(sortingDirection)) {
            if (sortingDirection.equals("ASC")) {
                query.with(new Sort(Sort.Direction.ASC, "_id"));
            } else {
                query.with(new Sort(Sort.Direction.DESC, "_id"));
            }
        }
        return mongoTemplate.find(query, Note.class);
    }
}
