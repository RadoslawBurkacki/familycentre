package radoslawburkacki.honoursproject.familycentre.Model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;


@Entity
@Table(name = "familyMembers")
public class FamilyMember {


    @Id
    @Column(unique=true)
    private Long memberId;
    private Long familyId;

    @JsonCreator
    public FamilyMember(){

    }

    public FamilyMember(Long memberId, Long familyId) {
        this.memberId = memberId;
        this.familyId = familyId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }
}
