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

    public ResponseEntity getFamilyByUserID(long id) {

        try {

            FamilyMember fm = familyMemberRepository.findFamilyMemberByMemberId(id);

            Family f = familyRepository.findFamilyByFamilyId(fm.getFamilyId());

            f.setJoiningPassword("");

            return new ResponseEntity<>(f, HttpStatus.FOUND);

        } catch (NullPointerException e) {
            return new ResponseEntity<>(new Family(), HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity addUserToFamily(JoinFamily jf) {
        /*
        1. check if user family exists
        2. check if user is already a member of any family
        3. check password
         */
        Family f = familyRepository.findFamilyByFamilyId(jf.getFamilyId());

        if (f == null) { // check if family exists (if family is null means that family does not exist)

            return new ResponseEntity<>("Family does not exist", HttpStatus.NOT_FOUND);

        } else { // family exists
            if (!familyMemberRepository.existsByMemberId(jf.getUserId())) { // check if user is a member of any family if false then...

                if (bCryptPasswordEncoder.matches(jf.getFamilyPassword(), f.getJoiningPassword())) { // if family joining password is correct then...

                    FamilyMember fm = new FamilyMember();
                    fm.setFamilyId(jf.getFamilyId());
                    fm.setMemberId(jf.getUserId());

                    familyMemberRepository.save(fm); // add user to family

                    return new ResponseEntity<>("User added", HttpStatus.CREATED);

                } else { // if password is wrong then...

                    return new ResponseEntity<>("Wrong joining password", HttpStatus.FORBIDDEN);
                }
            } else { // if user is already a member of family then...

                return new ResponseEntity<>("User is already a member of family", HttpStatus.CONFLICT);
            }

        }

    }

}