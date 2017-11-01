package radoslawburkacki.honoursproject.familycentre.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

   /*


    public ResponseEntity isEmailInUse(String email){
        if(userRepository.exists(1)){
            return ResponseEntity.status(HttpStatus.FOUND).body(null);    // return code created
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);    // return code created
        }
    }
    */


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



    ///////////////TESTING
    public List<User> getAllUsers() {
        List<User> users = new ArrayList();
        userRepository.findAll().forEach(users::add);
        return users;
    }




}
