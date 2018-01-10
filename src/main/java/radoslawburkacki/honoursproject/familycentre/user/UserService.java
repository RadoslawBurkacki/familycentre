package radoslawburkacki.honoursproject.familycentre.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import radoslawburkacki.honoursproject.familycentre.Model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Autowired
    private UserRepository userRepository;


    // register function
    public ResponseEntity register(User user) { // function returns ResponseEntity(HTTP status) it is expecting user object

        if (userRepository.findUserByEmail(user.getEmail()) == null) { // if user does not exist then..
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);  // add user to database
            return ResponseEntity.status(HttpStatus.CREATED).body(null);    // return code created
        } else {    // else...
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);   // return code conflict
        }
    }


    public User getUser(String email) {
        if (userRepository.findUserByEmail(email) != null) {
            User user = userRepository.findUserByEmail(email);
            user.setPassword("");
            return user;
        } else {
            return new User();
        }
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList();
        userRepository.findAll().forEach(users::add);
        return users;
    }


}
