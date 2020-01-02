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
import pl.polsl.household.HouseholdsMongoRepository;
import pl.polsl.model.Checklist;
import pl.polsl.model.Note;
import pl.polsl.model.User;
import pl.polsl.model.UsersPaging;

@Service
public class UsersService implements UserDetailsService {

    private final UsersMongoRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final QueryService queryService;
    private final MongoTemplate mongoTemplate;
    private final HouseholdsMongoRepository householdRepository;

    public UsersService(UsersMongoRepository repository, BCryptPasswordEncoder passwordEncoder, QueryService queryService, MongoTemplate mongoTemplate, HouseholdsMongoRepository householdRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.queryService = queryService;
        this.mongoTemplate = mongoTemplate;
        this.householdRepository = householdRepository;
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
        validateIfDifferent(oldUser,user);
        if (!user.getPassword().equals(oldUser.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setId(oldUser.getId());
        repository.save(user);
        if (!username.equals(user.getUsername())){
            updateCreatorDocuments(username, user.getUsername());
            updateRecipentDocuments(username,user.getUsername());
        }
        return true;
    }
    private void updateCreatorDocuments(String oldUsername, String newUsername) {
        Query query  = new Query();
        query.addCriteria(Criteria.where("creator").is(oldUsername));
        Update update = new Update();
        update.set("creator",newUsername);
        mongoTemplate.updateMulti(query,update, Note.class);
        mongoTemplate.updateMulti(query,update, Checklist.class);
    }

    private void updateRecipentDocuments(String oldUsername, String newUsername) {
        Query query  = new Query();
        query.addCriteria(Criteria.where("recipent").is(oldUsername));
        Update update = new Update();
        update.set("recipent",newUsername);
        mongoTemplate.updateMulti(query,update, Note.class);
        mongoTemplate.updateMulti(query,update, Checklist.class);
    }

    private void validateIfDifferent(User oldUser, User user){
        if (!oldUser.getUsername().equals(user.getUsername())){
            if (repository.findByUsername(user.getUsername()) != null) {
                throw new ItemNotUniqueException(ItemNotUniqueException.ExceptionType.NAME_NOT_UNIQUE);
            }
        }
        if (!oldUser.getEmail().equals(user.getEmail())){
            if (repository.findByEmail(user.getEmail()) != null) {
                throw new ItemNotUniqueException(ItemNotUniqueException.ExceptionType.EMAIL_NOT_UNIQUE);
            }
        }
    }

    boolean setUserHousehold(String username, String householdId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        if (householdId!=null){
            if (householdRepository.findById(householdId)==null){
                return false;
            }
        }
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
