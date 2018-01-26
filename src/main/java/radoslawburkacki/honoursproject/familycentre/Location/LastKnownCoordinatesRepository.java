package radoslawburkacki.honoursproject.familycentre.Location;

import org.springframework.data.repository.CrudRepository;
import radoslawburkacki.honoursproject.familycentre.Model.LastKnownCoordinates;

public interface LastKnownCoordinatesRepository extends CrudRepository<LastKnownCoordinates, Long> {

    public LastKnownCoordinates findLastKnownCoordinatesById(Long id);

}
