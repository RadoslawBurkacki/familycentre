package radoslawburkacki.honoursproject.familycentre.Family;

import org.springframework.data.repository.CrudRepository;
import radoslawburkacki.honoursproject.familycentre.Model.Family;


public interface FamilyRepository extends CrudRepository<Family,Long> {


    public Family findFamilyById( long id);



}
