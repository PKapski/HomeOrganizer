package pl.polsl.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.exceptions.ItemNotUniqueException;
import pl.polsl.model.User;

import java.util.Collections;
import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    private final UsersMongoRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsersService(UsersMongoRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    void validateAndSaveUser(User user) {
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setHouseholdId("testGroup"); //#FIXME
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user !=null){
            //List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));
            //return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
            return user;
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
