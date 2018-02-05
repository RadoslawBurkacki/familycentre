package radoslawburkacki.honoursproject.familycentre.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import radoslawburkacki.honoursproject.familycentre.Model.FCMToken;
import radoslawburkacki.honoursproject.familycentre.Service.FCMService;

@RestController
public class FCMController {

    @Autowired
    FCMService fcmTokenService;

    @RequestMapping(method = RequestMethod.POST, value = "/fcmtoken")
    public void saveFCMToken(@RequestBody FCMToken fcmToken) {
        fcmTokenService.saveFCMToken(fcmToken);
    }

}
