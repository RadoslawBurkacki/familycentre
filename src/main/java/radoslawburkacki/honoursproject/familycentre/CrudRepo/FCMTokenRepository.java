package radoslawburkacki.honoursproject.familycentre.CrudRepo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import radoslawburkacki.honoursproject.familycentre.Model.FCMToken;

public interface FCMTokenRepository extends CrudRepository <FCMToken, Long>{

    public FCMToken findFCMTokenByUserId(Long id);
    public FCMToken findByMyFCMToken(String fcmtoken);


}
