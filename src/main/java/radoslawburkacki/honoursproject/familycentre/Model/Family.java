package radoslawburkacki.honoursproject.familycentre.Model;



import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;


@Entity
@Table(name = "family")
public class Family {

    @Id
    @GeneratedValue
    @Column(unique=true)
    Long familyId;
    Long creatorId;
    String familyName;
    String joiningPassword;


    @JsonCreator
    public Family(){
    }

    public Family(Long creatorId, String familyName, String joiningPassword) {
        this.creatorId = creatorId;
        this.familyName = familyName;
        this.joiningPassword = joiningPassword;
    }



    public Long getId() {
        return familyId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getJoiningPassword() {
        return joiningPassword;
    }

    public void setJoiningPassword(String joiningPassword) {
        this.joiningPassword = joiningPassword;
    }

    @Override
    public String toString() {
        return "Family{" +
                "familyId=" + familyId +
                ", creatorId=" + creatorId +
                ", familyName='" + familyName + '\'' +
                ", joiningPassword='" + joiningPassword + '\'' +
                '}';
    }
}
