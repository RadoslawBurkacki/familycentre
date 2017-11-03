package radoslawburkacki.honoursproject.familycentre.family;


public class Family {

    Long id;
    Long creatorId;
    String creatorEmail;
    String familyName;
    String joiningPassword;

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

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public String getJoiningPassword() {
        return joiningPassword;
    }

    public void setJoiningPassword(String joiningPassword) {
        this.joiningPassword = joiningPassword;
    }
}
