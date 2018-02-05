package radoslawburkacki.honoursproject.familycentre.Model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FCMTokens")
public class FCMToken {

    @Id
    @Column(unique = true)
    Long userId;
    String myFCMToken;

    @JsonCreator
    public FCMToken(){

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMyFCMToken() {
        return myFCMToken;
    }

    public void setMyFCMToken(String myFCMToken) {
        this.myFCMToken = myFCMToken;
    }

    @Override
    public String toString() {
        return "FCMToken{" +
                "userId=" + userId +
                ", myFCMToken='" + myFCMToken + '\'' +
                '}';
    }
}
