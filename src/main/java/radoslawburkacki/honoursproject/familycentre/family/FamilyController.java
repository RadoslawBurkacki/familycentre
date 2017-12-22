package radoslawburkacki.honoursproject.familycentre.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class FamilyController {

    @Autowired
    private FamilyService familyService;


    @RequestMapping(method = RequestMethod.HEAD, value = "/family/check/{id}")
    public ResponseEntity CheckIfUserIsFamilyMemberById(@PathVariable long id) {
        return familyService.CheckIfUserIsFamilyMemberById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createfamily/")
    public ResponseEntity createFamily(@RequestBody Family family) {
        // f = new Family(family.getCreatorId(),family.getFamilyName(),family.getJoiningPassword());
        return familyService.createFamily(family);
    }
}
