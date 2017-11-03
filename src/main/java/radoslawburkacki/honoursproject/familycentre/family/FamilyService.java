package radoslawburkacki.honoursproject.familycentre.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyMembersRepository familyMembersRepository;

    public ResponseEntity checkIfMemberOfFamily(String email){

    }

    public ResponseEntity createFamily(Family family){
        familyRepository.save(family);

    }

}
