package radoslawburkacki.honoursproject.familycentre.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radoslawburkacki.honoursproject.familycentre.Service.LocationService;
import radoslawburkacki.honoursproject.familycentre.Model.LastKnownCoordinates;

import java.util.List;


@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @RequestMapping(method = RequestMethod.GET, value = "/families/location/{userid}")
    public ResponseEntity getLocationCoordinates(@PathVariable Long userid) {

        System.out.println("Get Location coordinates for user : " + userid);

        return locationService.getLastKnownCoordinates(userid);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/families/location/{userid}")
    public ResponseEntity addNewLocationCoordinates(@PathVariable Long userid, @RequestBody LastKnownCoordinates lastKnownCoordinates) {

        System.out.println("Post location coordinates for user "+ userid +" Longitude: " + lastKnownCoordinates.getLongitude() +" , Latitude: "+ lastKnownCoordinates.getLatitude());

        lastKnownCoordinates.setId(userid);
        return locationService.saveCoordinates(lastKnownCoordinates);
    }


    @RequestMapping(method= RequestMethod.GET, value="/families/location/")
    public List<LastKnownCoordinates> getAllUsers(){
        return locationService.getAllLastKnownCoordinates();
    }

}

