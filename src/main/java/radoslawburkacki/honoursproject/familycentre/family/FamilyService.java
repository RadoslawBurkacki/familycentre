package radoslawburkacki.honoursproject.familycentre.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import radoslawburkacki.honoursproject.familycentre.Model.Family;
import radoslawburkacki.honoursproject.familycentre.Model.FamilyMember;
import radoslawburkacki.honoursproject.familycentre.Model.JoinFamily;

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

        if (familyMemberRepository.existsByMemberId(family.getCreatorId())) {
            return ResponseEntity.status(400).build();
        } else {
            family.setJoiningPassword(bCryptPasswordEncoder.encode(family.getJoiningPassword()));
            familyRepository.save(family);
            familyMemberRepository.save(new FamilyMember(family.getCreatorId(), family.getId()));
            return ResponseEntity.status(201).build();
        }
    }

    public Family getFamilyByUserID(long id) {


        return new Family();
//       return familyRepository.findFamilyByFamilyId(familyMemberRepository.findFamilyIdByMemberId(id));

    }

    public void addUserToFamily(JoinFamily jf) {
        /*
        1. check if user exists
        2. check if user is already a member of any family
        3. check if family which he is trying to join exists
        4. check password
         */

        if (!familyMemberRepository.existsByMemberId(jf.getUserId())) { // if user is not a member of any family then...


            Family f = familyRepository.findFamilyByFamilyId(jf.getFamilyId());

            if (f == null) {
                System.out.print("family does not exist");
            } else {


                if (bCryptPasswordEncoder.matches(jf.getFamilyPassword(), f.getJoiningPassword())) { // if password is correct then...

                   familyMemberRepository.save(new FamilyMember(jf.getUserId(),jf.getFamilyId()));


                } else {
                    System.out.println("wrong password");
                }
            }
        } else {
            System.out.println("user is already a member of family");
        }

    }
}