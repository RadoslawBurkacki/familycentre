package radoslawburkacki.honoursproject.familycentre.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JoinFamily {

    @JsonProperty("familyId")
    private long familyId;
    @JsonProperty("familyPassword")
    private String familyPassword;
    @JsonProperty("userId")
    private Long userId;

    public long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(long familyId) {
        this.familyId = familyId;
    }

    public String getFamilyPassword() {
        return familyPassword;
    }

    public void setFamilyPassword(String familyPassword) {
        this.familyPassword = familyPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "JoinFamily{" +
                "familyId=" + familyId +
                ", familyPassword='" + familyPassword + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
