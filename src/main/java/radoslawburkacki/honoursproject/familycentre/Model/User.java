package radoslawburkacki.honoursproject.familycentre.Model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {



    @Id
    @GeneratedValue
    @Column(unique=true)
    private long id;
    @Column(unique=true)
    private String email;
    private String password;
    private String fname;
    private String lname;

    @JsonCreator
    public User(){

    }

    public User(String email, String password, String fname, String lname) {
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }


}
