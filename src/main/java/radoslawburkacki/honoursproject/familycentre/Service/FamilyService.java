package radoslawburkacki.honoursproject.familycentre.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import radoslawburkacki.honoursproject.familycentre.CrudRepo.FamilyMemberRepository;
import radoslawburkacki.honoursproject.familycentre.CrudRepo.FamilyRepository;
import radoslawburkacki.honoursproject.familycentre.Model.*;
import radoslawburkacki.honoursproject.familycentre.CrudRepo.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    @Autowired
    private UserRepository userRepository;

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
            Family f = familyRepository.findFamilyById(fm.getFamilyId());

            f.setJoiningPassword("");

            List<FamilyMember> familymembers = new ArrayList<FamilyMember>();
            familymembers = familyMemberRepository.findFamilyMemberByFamilyId(f.getId());

            for (FamilyMember zz : familymembers) {
                f.addFamilyMember(userRepository.findUserById(zz.getMemberId()));
            }

            for (User user : f.getFamilyMembers()) {
                user.setPassword("");
            }

            return new ResponseEntity<>(f, HttpStatus.FOUND);

        } catch (NullPointerException e) {
            return new ResponseEntity<>(new Family(), HttpStatus.NOT_FOUND);
        }

    }


    public ResponseEntity addUserToFamily(JoinFamily jf) {

        Family f = familyRepository.findFamilyById(jf.getFamilyId());

        if (f == null) { // check if Family exists (if Family is null means that Family does not exist)
            return new ResponseEntity<>("Family does not exist", HttpStatus.NOT_FOUND);
        } else { // Family exists
            if (!familyMemberRepository.existsByMemberId(jf.getUserId())) { // check if User is a member of any Family if false then...
                if (bCryptPasswordEncoder.matches(jf.getFamilyPassword(), f.getJoiningPassword())) { // if Family joining password is correct then...

                    FamilyMember fm = new FamilyMember();
                    fm.setFamilyId(jf.getFamilyId());
                    fm.setMemberId(jf.getUserId());

                    familyMemberRepository.save(fm); // add User to Family

                    return new ResponseEntity<>("User added", HttpStatus.CREATED);

                } else { // if password is wrong then...

                    return new ResponseEntity<>("Wrong joining password", HttpStatus.FORBIDDEN);
                }
            } else { // if User is already a member of Family then...

                return new ResponseEntity<>("User is already a member of Family", HttpStatus.CONFLICT);
            }

        }

    }

    public List<Family> getAllFamilies() {

        List<Family> families = new ArrayList();
        familyRepository.findAll().forEach(families::add);
        return families;

    }

}