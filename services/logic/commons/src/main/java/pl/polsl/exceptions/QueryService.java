package pl.polsl.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class QueryService {

    private static final int MAX_RESULTS = 100;
    private final MongoTemplate mongoTemplate;

    public QueryService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addPagination(Query query, Integer firstResult, Integer maxResults) {
        if (firstResult != null) {
            query.skip(firstResult);
        }
        query.limit(Objects.requireNonNullElse(maxResults, MAX_RESULTS));
    }

    public void addSorting(Query query, String sortingDirection, String sortedField) {
        if (StringUtils.isEmpty(sortedField)) {
            sortedField = "_id";
        }
        if (StringUtils.isEmpty(sortingDirection)) {
            sortingDirection = "ASC";
        }
        if (sortingDirection.equals("ASC")) {
            query.with(new Sort(Sort.Direction.ASC, sortedField));
        } else {
            query.with(new Sort(Sort.Direction.DESC, sortedField));
        }
    }

    public Integer getMaxItems(Query query, Class returnClass){
        return Math.toIntExact(mongoTemplate.count(query,returnClass));
    }
}
