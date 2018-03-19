package radoslawburkacki.honoursproject.familycentre.CrudRepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import radoslawburkacki.honoursproject.familycentre.Model.FamilyMember;

import java.util.List;

public interface FamilyMemberRepository extends CrudRepository<FamilyMember, Long> {

    public boolean existsByMemberId(Long id);
    public FamilyMember findFamilyMemberByMemberId(Long id);
    public List<FamilyMember> findFamilyMemberByFamilyId(long id);
    @Transactional
    public void removeFamilyMemberByMemberId(long id);
}