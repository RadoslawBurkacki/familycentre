package radoslawburkacki.honoursproject.familycentre.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "LastKnownCoordinates")
public class LastKnownCoordinates {


    @Id
    @Column(unique = true)
    private long id;
    private double latitude;
    private double longitude;
    private LocalDateTime dateTime;

    @JsonProperty
    @Transient
    List<Integer> list = new ArrayList<>();

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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void getDifference() {

        LocalDateTime toDateTime = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(dateTime);

        long years = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);


        long hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        long seconds = tempDateTime.until(toDateTime, ChronoUnit.SECONDS);



        list = new ArrayList<>(Arrays.asList((int)years,(int)months,(int)days,(int)hours,(int)minutes,(int)seconds));


    }
}
