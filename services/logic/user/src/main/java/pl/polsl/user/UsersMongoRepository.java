package pl.polsl.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.User;

import java.util.List;

public interface UsersMongoRepository extends MongoRepository<User,String> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAllByHouseholdId(String householdId);
}
