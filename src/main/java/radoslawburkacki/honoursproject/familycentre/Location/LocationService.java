package radoslawburkacki.honoursproject.familycentre.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import radoslawburkacki.honoursproject.familycentre.Model.LastKnownCoordinates;


@Service
public class LocationService {

    @Autowired
    private LastKnownCoordinatesRepository lastKnownCoordinatesRepository;


    public ResponseEntity saveCoordinates(LastKnownCoordinates lastKnownCoordinates) {

        lastKnownCoordinatesRepository.save(lastKnownCoordinates);

        return new ResponseEntity<>("User is already a member of Family", HttpStatus.CONFLICT);
    }

    public ResponseEntity getLastKnownCoordinates(Long userid) {


        if(lastKnownCoordinatesRepository.findLastKnownCoordinatesById(userid)!= null){
            return new ResponseEntity<>(lastKnownCoordinatesRepository.findLastKnownCoordinatesById(userid), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(lastKnownCoordinatesRepository.findLastKnownCoordinatesById(userid), HttpStatus.NOT_FOUND);

    }

}
