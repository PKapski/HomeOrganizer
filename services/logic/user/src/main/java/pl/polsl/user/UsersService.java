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
        if (repository.findByUsername(user.getUsername()) != null) {
            throw new ItemNotUniqueException(ItemNotUniqueException.ExceptionType.NAME_NOT_UNIQUE);
        }else if (repository.findByEmail(user.getEmail())!=null){
            throw new ItemNotUniqueException(ItemNotUniqueException.ExceptionType.EMAIL_NOT_UNIQUE);
        }
        repository.save(user);
    }

    List<User> getHouseholdUsers(String householdId){
        return repository.findAllByHouseholdId(householdId);
    }
}
