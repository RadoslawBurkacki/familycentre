package radoslawburkacki.honoursproject.familycentre.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import radoslawburkacki.honoursproject.familycentre.user.UserService;


@RestController
public class FamilyController {

    @Autowired
    private FamilyService familyService;



    @RequestMapping(method= RequestMethod.HEAD, value="/family/check/{email)")
    public ResponseEntity checkIfMemberOfFamily(@PathVariable String email){
        return familyService.checkIfMemberOfFamily(email);

    }

    @RequestMapping(method= RequestMethod.POST, value="family/create")
    public ResponseEntity createFamily(Family family){
        return familyService.createFamily(family);
    }
}
