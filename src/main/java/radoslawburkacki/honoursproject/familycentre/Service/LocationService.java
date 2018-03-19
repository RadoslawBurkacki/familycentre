package radoslawburkacki.honoursproject.familycentre.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import radoslawburkacki.honoursproject.familycentre.CrudRepo.LastKnownCoordinatesRepository;
import radoslawburkacki.honoursproject.familycentre.Model.LastKnownCoordinates;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class LocationService {

    @Autowired
    private LastKnownCoordinatesRepository lastKnownCoordinatesRepository;


    public ResponseEntity saveCoordinates(LastKnownCoordinates lastKnownCoordinates) {

        lastKnownCoordinates.setDateTime(LocalDateTime.now());
        lastKnownCoordinatesRepository.save(lastKnownCoordinates);

        return new ResponseEntity<>("Location updated", HttpStatus.OK);
    }

    public ResponseEntity getLastKnownCoordinates(Long userid) {

        if (lastKnownCoordinatesRepository.findLastKnownCoordinatesById(userid) != null) {

            LastKnownCoordinates l = lastKnownCoordinatesRepository.findLastKnownCoordinatesById(userid);
            l.getDifference();
            return new ResponseEntity<>(l, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(lastKnownCoordinatesRepository.findLastKnownCoordinatesById(userid), HttpStatus.NOT_FOUND);

    }


    public List<LastKnownCoordinates> getAllLastKnownCoordinates() {
        List<LastKnownCoordinates> lastKnownCoordinates = new ArrayList();
        lastKnownCoordinatesRepository.findAll().forEach(lastKnownCoordinates::add);
        return lastKnownCoordinates;
    }

}
