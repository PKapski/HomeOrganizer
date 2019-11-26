package pl.polsl.note;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.polsl.exceptions.QueryService;
import pl.polsl.model.Note;
import pl.polsl.model.NotesPaging;

import java.util.Optional;

@Service
public class NotesService {

    private final NotesMongoRepository repository;
    private final MongoTemplate mongoTemplate;
    private final QueryService queryService;

    public NotesService(NotesMongoRepository repository, MongoTemplate mongoTemplate, QueryService queryService) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
        this.queryService = queryService;
    }

    public String saveNote(Note note) {
        return repository.save(note).getId();
    }

    public boolean deleteNote(String id) {
        Optional<Note> note = repository.findById(id);
        if (note.isPresent()) {
            repository.delete(note.get());
            return true;
        }
        return false;
    }

    public NotesPaging getFilteredNotesPaging(String username, String householdId, String sortingDirection, String sortedField, Integer  firstResult, Integer  maxResults) {
        NotesPaging paging = new NotesPaging();
        Query query = new Query();
        if (StringUtils.isNotEmpty(username)) {
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("recipent").is(username), Criteria.where("visibleToEveryone").is(true), Criteria.where("creator").is(username));
            query.addCriteria(criteria);
        }
        if (StringUtils.isNotEmpty(householdId)) {
            query.addCriteria(Criteria.where("householdId").is(householdId));
        }

        queryService.addSorting(query,sortingDirection,sortedField);
        paging.setMaxItems(queryService.getMaxItems(query,Note.class));
        queryService.addPagination(query,firstResult,maxResults);
        paging.setArray(mongoTemplate.find(query, Note.class));
        return paging;
    }


}
