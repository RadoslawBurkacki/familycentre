package radoslawburkacki.honoursproject.familycentre.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radoslawburkacki.honoursproject.familycentre.Model.FCMToken;
import radoslawburkacki.honoursproject.familycentre.Service.SOSService;

@RestController
public class SOSController {

    @Autowired
    SOSService sosService;

    @RequestMapping(method = RequestMethod.HEAD, value = "/sos/{id}")
    public ResponseEntity sendSOS(@PathVariable long id) {
        System.out.println("SOS Received");
       return sosService.sendSOS(id);
    }

}
