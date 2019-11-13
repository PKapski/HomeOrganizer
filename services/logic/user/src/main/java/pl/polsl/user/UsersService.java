package pl.polsl.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.exceptions.ItemNotUniqueException;
import pl.polsl.exceptions.QueryService;
import pl.polsl.model.User;
import pl.polsl.model.UsersPaging;

@Service
public class UsersService implements UserDetailsService {

    private final UsersMongoRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final QueryService queryService;
    private final MongoTemplate mongoTemplate;

    public UsersService(UsersMongoRepository repository, BCryptPasswordEncoder passwordEncoder, QueryService queryService, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.queryService = queryService;
        this.mongoTemplate = mongoTemplate;
    }

    void validateAndSaveUser(User user) {
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    private void validateUser(User user) {
        if (repository.findByUsername(user.getUsername()) != null) {
            throw new ItemNotUniqueException(ItemNotUniqueException.ExceptionType.NAME_NOT_UNIQUE);
        } else if (repository.findByEmail(user.getEmail()) != null) {
            throw new ItemNotUniqueException(ItemNotUniqueException.ExceptionType.EMAIL_NOT_UNIQUE);
        }
    }

    UsersPaging getHouseholdUsers(String householdId, String sortingDirection, String sortedField, Integer firstResult, Integer maxResults) {
        UsersPaging paging = new UsersPaging();
        Query query = new Query();
        if (StringUtils.isNotEmpty(householdId)) {
            query.addCriteria(Criteria.where("householdId").is(householdId));
        }
        queryService.addSorting(query, sortingDirection, sortedField);
        paging.setMaxItems(queryService.getMaxItems(query, User.class));
        queryService.addPagination(query, firstResult, maxResults);
        paging.setArray(mongoTemplate.find(query, User.class));
        return paging;
    }

    boolean deleteUser(String username) {
        User user = repository.findByUsername(username);
        if (user != null) {
            repository.delete(user);
            return true;
        }
        return false;
    }

    User getUser(String username) {
        return repository.findByUsername(username);
    }

    boolean modifyUser(String username, User user) {
        User oldUser = repository.findByUsername(username);
        if (oldUser == null) {
            return false;
        }
        validateUser(user);
        user.setId(oldUser.getId());
        repository.save(user);
        return true;
    }

    boolean setUserHousehold(String username, String householdId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.updateFirst(query, Update.update("householdId", householdId), User.class).wasAcknowledged();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }


}
