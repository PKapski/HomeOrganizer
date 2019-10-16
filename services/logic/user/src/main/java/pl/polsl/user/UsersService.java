package pl.polsl.user;

import org.springframework.stereotype.Service;
import pl.polsl.exceptions.ItemNotUniqueException;
import pl.polsl.model.User;

import java.util.List;

@Service
public class UsersService {

    private final UsersMongoRepository repository;

    public UsersService(UsersMongoRepository repository) {
        this.repository = repository;
    }

    void validateAndSaveUser(User user) {
        validateUser(user);
        repository.save(user);
    }

    private void validateUser(User user){
        if (repository.findByUsername(user.getUsername()) != null) {
            throw new ItemNotUniqueException(ItemNotUniqueException.ExceptionType.NAME_NOT_UNIQUE);
        }else if (repository.findByEmail(user.getEmail())!=null){
            throw new ItemNotUniqueException(ItemNotUniqueException.ExceptionType.EMAIL_NOT_UNIQUE);
        }
    }

    List<User> getHouseholdUsers(String householdId){
        return repository.findAllByHouseholdId(householdId);
    }

    boolean deleteUser(String username){
        User user = repository.findByUsername(username);
        if (user!=null) {
            repository.delete(user);
            return true;
        }
        return false;
    }

    User getUser(String username){
        return repository.findByUsername(username);
    }

    boolean modifyUser(String username, User user){
        User oldUser = repository.findByUsername(username);
        if (oldUser==null) {
            return false;
        }
        validateUser(user);
        user.setId(oldUser.getId());
        repository.save(user);
        return true;
    }


}