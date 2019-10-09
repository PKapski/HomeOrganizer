package pl.polsl.checklist;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.api.ChecklistsApiDelegate;
import pl.polsl.model.Checklist;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistsApiDelegateImpl implements ChecklistsApiDelegate {

    private final ChecklistsMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    public ChecklistsApiDelegateImpl(ChecklistsMongoRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ResponseEntity<Void> createChecklist(Checklist checklist) {
        repository.save(checklist);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteChecklist(String id) {
        Optional<Checklist> checklist = repository.findById(id);
        if (checklist.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.delete(checklist.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Checklist>> getChecklists(String creator, String recipent) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "creationDate"));
        if (StringUtils.isNotEmpty(recipent))
            query.addCriteria(Criteria.where("recipent").is(recipent));
        if (StringUtils.isNotEmpty(creator))
            query.addCriteria(Criteria.where("creator").is(creator));
        return new ResponseEntity<>(mongoTemplate.find(query, Checklist.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> modifyChecklist(String id, Checklist newChecklist) {
        Optional<Checklist> checklist = repository.findById(id);
        if (checklist.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newChecklist.setId(checklist.get().getId());
        repository.save(newChecklist);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
