package radoslawburkacki.honoursproject.familycentre.family;

import org.springframework.data.repository.CrudRepository;
import radoslawburkacki.honoursproject.familycentre.Model.FamilyMember;

import java.util.List;

public interface FamilyMemberRepository extends CrudRepository<FamilyMember, Long> {

    public boolean existsByMemberId(Long id);
    public FamilyMember findFamilyMemberByMemberId(Long id);
    public List<FamilyMember> findFamilyMemberByFamilyId(long id);
}