package radoslawburkacki.honoursproject.familycentre.User;

import org.springframework.data.repository.CrudRepository;
import radoslawburkacki.honoursproject.familycentre.Model.User;

public interface UserRepository extends CrudRepository<User,Long> {

    public User findUserByEmail(String email);
    public User findUserById(Long id);
}
