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
    private final ChecklistsService service;

    public ChecklistsApiDelegateImpl(ChecklistsMongoRepository repository, MongoTemplate mongoTemplate, ChecklistsService service) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
        this.service = service;
    }

    @Override
    public ResponseEntity<String> saveChecklist(Checklist checklist) {
        String id = service.saveChecklist(checklist);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteChecklist(String id) {
        if (service.deleteChecklist(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Checklist>> getChecklists(String username, String householdId, String sortingDirection) {
        List<Checklist> checklists = service.getFilteredNotes(username,householdId,sortingDirection);
        return new ResponseEntity<>(checklists, HttpStatus.OK);
    }

}
