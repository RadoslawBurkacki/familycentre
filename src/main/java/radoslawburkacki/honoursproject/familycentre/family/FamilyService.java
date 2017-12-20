package radoslawburkacki.honoursproject.familycentre.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FamilyService {

  //  @Autowired
   // private FamilyRepository familyRepository;

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    public ResponseEntity CheckIfUserIsFamilyMemberById(Long id){
        if(familyMemberRepository.existsByMemberId(id)){
            return ResponseEntity.status(HttpStatus.FOUND).body(null);    // return code found
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);    // return code not found
        }
    }

    public ResponseEntity createFamily(Family family){
      //  familyRepository.save(family);
        return ResponseEntity.ok().build();
    }

}
