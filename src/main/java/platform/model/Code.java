package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "code")
public class Code {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonIgnore
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "code")
    private String code;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "views")
    private int views;
    @Column(name = "time")
    private long time;
    @JsonIgnore
    @Column(name = "viewsRestriction")
    private boolean viewsRestriction;
    @JsonIgnore
    @Column(name = "timeRestriction")
    private boolean timeRestriction;

    /**
     * The date the code was saved in the database in string format
     *
     * @return save date in the DB
     */
    @JsonProperty("date")
    public String getDateAsString() {
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "Code{" +
                "code='" + code + '\'' +
                ", date=" + date +
                '}';
    }
}
