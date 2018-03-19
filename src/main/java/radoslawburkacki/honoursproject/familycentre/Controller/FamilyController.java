package radoslawburkacki.honoursproject.familycentre.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radoslawburkacki.honoursproject.familycentre.Model.Family;
import radoslawburkacki.honoursproject.familycentre.Model.JoinFamily;
import radoslawburkacki.honoursproject.familycentre.Model.User;
import radoslawburkacki.honoursproject.familycentre.Service.FamilyService;

import java.util.List;


@RestController
public class FamilyController {

    @Autowired
    private FamilyService familyService;


    @RequestMapping(method = RequestMethod.POST, value = "/families") // create family
    public ResponseEntity createFamily(@RequestBody Family family) {
        System.out.println("New family has been created. "+family.toString());
        return familyService.createFamily(family);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/families/")  // get all families
    public List<Family> getAllFamilies() {
        return familyService.getAllFamilies();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/families/")  // add user to family
    public ResponseEntity addUserToFamily(@RequestBody JoinFamily jf) {
        return familyService.addUserToFamily(jf);
    }


    @RequestMapping(method = RequestMethod.HEAD, value = "/families/{id}")  // check if User is Family member
    public ResponseEntity CheckIfUserIsFamilyMemberById(@PathVariable long id) {
        return familyService.CheckIfUserIsFamilyMemberById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/families/by-user-id/{id}")    // get Family by User id
    public ResponseEntity getFamilyByUserID(@PathVariable long id) {

        return familyService.getFamilyByUserID(id);

    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/families/")    // get Family by User id
    public ResponseEntity removeUserFromFamily(@RequestBody JoinFamily jf) {
        return familyService.removeUserFromFamily(jf.getFamilyId(), jf.getUserId());

    }






}
