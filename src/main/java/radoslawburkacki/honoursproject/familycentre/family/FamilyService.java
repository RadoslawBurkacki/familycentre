package radoslawburkacki.honoursproject.familycentre.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyMemberRepository familyMemberRepository;


    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public FamilyService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public ResponseEntity CheckIfUserIsFamilyMemberById(Long id) {

        if (familyMemberRepository.existsByMemberId(id)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(null);    // return code found
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);    // return code not found
        }
    }

    public ResponseEntity createFamily(Family family) {

        family.setJoiningPassword(bCryptPasswordEncoder.encode(family.getJoiningPassword()));
        familyRepository.save(family);
        familyMemberRepository.save(new FamilyMember(family.getCreatorId(),family.getId()));
        return ResponseEntity.ok().build();
    }

}
