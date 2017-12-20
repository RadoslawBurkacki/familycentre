package radoslawburkacki.honoursproject.familycentre.family;

import org.springframework.data.repository.CrudRepository;


public interface FamilyMemberRepository extends CrudRepository<FamilyMember,Long> {


    public boolean existsByMemberId(Long id);
}