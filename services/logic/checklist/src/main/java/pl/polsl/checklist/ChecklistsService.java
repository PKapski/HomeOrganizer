package pl.polsl.checklist;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.polsl.model.Checklist;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistsService {

    private final ChecklistsMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    public ChecklistsService(ChecklistsMongoRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    String saveChecklist(Checklist checklist) {
        return repository.save(checklist).getId();
    }

    boolean deleteChecklist(String id) {
        Optional<Checklist> checklist = repository.findById(id);
        if (checklist.isPresent()) {
            repository.delete(checklist.get());
            return true;
        }
        return false;
    }

    List<Checklist> getFilteredNotes(String username, String householdId, String sortingDirection) {
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
        return mongoTemplate.find(query, Checklist.class);
    }

}
