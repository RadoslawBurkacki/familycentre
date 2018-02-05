package radoslawburkacki.honoursproject.familycentre.CrudRepo;

import org.springframework.data.repository.CrudRepository;
import radoslawburkacki.honoursproject.familycentre.Model.FCMToken;

public interface FCMTokenRepository extends CrudRepository <FCMToken, Long>{

    public String findFCMTokenByUserId(Long id);
}
