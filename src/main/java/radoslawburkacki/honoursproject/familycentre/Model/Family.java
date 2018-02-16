package radoslawburkacki.honoursproject.familycentre.Model;



import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Family")
public class Family {

    @Id
    @GeneratedValue
    @Column(unique=true, name = "familyId")
    private Long id;
    private Long creatorId;
    private String familyName;
    private String joiningPassword;
    @Transient
    private List<User> familyMembers = new ArrayList<User>();


    @JsonCreator
    public Family(){
    }

    public Family(Long creatorId, String familyName, String joiningPassword, List<User> familyMembers) {
        this.creatorId = creatorId;
        this.familyName = familyName;
        this.joiningPassword = joiningPassword;
        this.familyMembers = familyMembers;
    }



    public Long getId() {
        return id;
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

    public List<User> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<User> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public void addFamilyMember(User u){
        familyMembers.add(u);
    }

    @Override
    public String toString() {
        return "Family{" +
                "familyId=" + id +
                ", creatorId=" + creatorId +
                ", familyName='" + familyName + '\'' +
                ", joiningPassword='" + joiningPassword + '\'' +
                ", familyMembers=" + familyMembers +
                '}';
    }
}
