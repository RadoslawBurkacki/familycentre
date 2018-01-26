package radoslawburkacki.honoursproject.familycentre.Model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;

@Entity
@Table(name = "LastKnownCoordinates")
public class LastKnownCoordinates {


    @Id
    @GeneratedValue
    @Column(unique = true)
    private long id;
    private double latitude;
    private double longitude;

    @JsonCreator
    public LastKnownCoordinates() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double lat) {
        this.latitude = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
