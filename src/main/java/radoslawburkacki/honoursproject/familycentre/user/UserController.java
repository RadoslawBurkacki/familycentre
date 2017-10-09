package radoslawburkacki.honoursproject.familycentre.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    /*
    List of functions

    IsEmailAvailable
    Register
    Login
    Update
     */
    @Autowired
    private UserService userService;


    /*
    @RequestMapping(method= RequestMethod.HEAD, value="/register/{email}")
    public ResponseEntity isEmailInUse(@PathVariable String email){
        System.out.print(email);
        return userService.isEmailInUse(email);
    }// not working properly as the for ex .com is not being paased

    */
    @RequestMapping(method= RequestMethod.POST, value="/register/")
    public ResponseEntity registerNewUser(@RequestBody User user){
        User newUser = new User(user.getEmail(),user.getPassword(),user.getFname(),user.getLname()); // creating new user from passed data
        return userService.register(newUser); // passing new user
    }

    @RequestMapping("/hello")
    public String sayhelo(){
        return "hello";
    }





    @RequestMapping("/users/")
    public List<User> getAllRegisteredUsers(){
        return userService.getAllUsers();
    }









}
