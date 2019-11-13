package pl.polsl.checklist;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.polsl.exceptions.QueryService;
import pl.polsl.model.Checklist;
import pl.polsl.model.ChecklistsPaging;

import java.util.Optional;

@Service
public class ChecklistsService {

    private final ChecklistsMongoRepository repository;
    private final MongoTemplate mongoTemplate;
    private final QueryService queryService;

    public ChecklistsService(ChecklistsMongoRepository repository, MongoTemplate mongoTemplate, QueryService queryService) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
        this.queryService = queryService;
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

    ChecklistsPaging getFilteredNotesPaging(String username, String householdId, String sortingDirection, String sortedField, Integer  firstResult, Integer  maxResults) {
        ChecklistsPaging paging = new ChecklistsPaging();
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
        paging.setMaxItems(queryService.getMaxItems(query,Checklist.class));
        queryService.addPagination(query,firstResult,maxResults);
        paging.setArray(mongoTemplate.find(query, Checklist.class));
        return paging;
    }

}
