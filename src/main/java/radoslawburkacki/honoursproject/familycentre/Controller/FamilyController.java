package radoslawburkacki.honoursproject.familycentre.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radoslawburkacki.honoursproject.familycentre.Model.Family;
import radoslawburkacki.honoursproject.familycentre.Model.JoinFamily;
import radoslawburkacki.honoursproject.familycentre.family.FamilyService;


@RestController
public class FamilyController {

    @Autowired
    private FamilyService familyService;


    @RequestMapping(method = RequestMethod.HEAD, value = "/families/{id}")  // check if user is family member - HEAD
    public ResponseEntity CheckIfUserIsFamilyMemberById(@PathVariable long id) {
        return familyService.CheckIfUserIsFamilyMemberById(id);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/families/by-user-id/{id}")    // get family by user id
    public ResponseEntity getFamilyByUserID(@PathVariable long id) {
        return familyService.getFamilyByUserID(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/families/")
    public ResponseEntity addUserToFamily(@RequestBody JoinFamily jf) {

        return familyService.addUserToFamily(jf);

    }


    @RequestMapping(method = RequestMethod.POST, value = "/families")
    public ResponseEntity createFamily(@RequestBody Family family) {
        return familyService.createFamily(family);
    }


}
