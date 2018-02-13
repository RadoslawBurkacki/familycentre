package radoslawburkacki.honoursproject.familycentre.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import radoslawburkacki.honoursproject.familycentre.CrudRepo.FCMTokenRepository;
import radoslawburkacki.honoursproject.familycentre.CrudRepo.FamilyMemberRepository;
import radoslawburkacki.honoursproject.familycentre.CrudRepo.FamilyRepository;
import radoslawburkacki.honoursproject.familycentre.CrudRepo.UserRepository;
import radoslawburkacki.honoursproject.familycentre.Model.Family;
import radoslawburkacki.honoursproject.familycentre.Model.FamilyMember;
import radoslawburkacki.honoursproject.familycentre.Model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class SOSService {

    @Autowired
    FamilyMemberRepository familyMemberRepository;

    @Autowired
    FCMService fcmService;

    @Autowired
    FCMTokenRepository fcmTokenRepository;

    @Autowired
    FamilyRepository familyRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity sendSOS(long userid) {

        List<FamilyMember> familymembers = new ArrayList<>();
        familymembers = familyMemberRepository.findFamilyMemberByFamilyId(familyMemberRepository.findFamilyMemberByMemberId(userid).getFamilyId());

        List<String> FCMtokenList = new ArrayList<String>();

        for (FamilyMember f : familymembers) {
            if(f.getMemberId()== userid){
                continue;
            }
            System.out.println(f.getMemberId());
            FCMtokenList.add(fcmTokenRepository.findFCMTokenByUserId(f.getMemberId()).getMyFCMToken());
        }

        fcmService.sendSOS(FCMtokenList, userRepository.findUserById(userid).getFname() + " " + userRepository.findUserById(userid).getLname());

        return new ResponseEntity<>("SOS sent", HttpStatus.OK);
    }

}
