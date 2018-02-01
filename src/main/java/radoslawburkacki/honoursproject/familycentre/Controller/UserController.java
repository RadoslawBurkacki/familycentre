package radoslawburkacki.honoursproject.familycentre.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radoslawburkacki.honoursproject.familycentre.Model.User;
import radoslawburkacki.honoursproject.familycentre.User.UserService;

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



    @RequestMapping(method= RequestMethod.POST, value="/users")
    public ResponseEntity registerNewUser(@RequestBody User user){
        System.out.println("New user registered: " + user.getFname() + " " + user.getLname() +" " + user.getEmail());
        User newUser = new User(user.getEmail(),user.getPassword(),user.getFname(),user.getLname()); // creating new User from passed data
        return userService.register(newUser); // passing new User

    }


    @RequestMapping(method= RequestMethod.GET, value="/users/{email:.+}")
    public User getUser(@PathVariable String email){
        return userService.getUser(email);
    }





}
