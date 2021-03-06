package pl.polsl.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.api.UsersApiDelegate;
import pl.polsl.model.User;
import pl.polsl.model.UsersPaging;

import java.util.List;

@Service
public class UsersApiDelegateImpl implements UsersApiDelegate {

    private final UsersService service;

    public UsersApiDelegateImpl(UsersService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Void> saveUser(User user) {
        service.validateAndSaveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String username) {
        if (service.deleteUser(username)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<UsersPaging> getHouseholdUsers(String householdId, String sortingDirection, String sortedField, Integer  firstResult, Integer  maxResults) {
        UsersPaging usersPaging = service.getHouseholdUsers(householdId,sortingDirection,sortedField, firstResult, maxResults);
        return new ResponseEntity<>(usersPaging, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(String username) {
        User user = service.getUser(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> modifyUser(String username, User user) {
        if (service.modifyUser(username, user)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Void> setUserHousehold(String username, String householdId) {
        if (service.setUserHousehold(username,householdId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
