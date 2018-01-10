package radoslawburkacki.honoursproject.familycentre.family;

import org.springframework.data.repository.CrudRepository;
import radoslawburkacki.honoursproject.familycentre.Model.FamilyMember;

public interface FamilyMemberRepository extends CrudRepository<FamilyMember, Long> {
    public boolean existsByMemberId(Long id);
    public long findFamilyIdByMemberId(Long id);
}