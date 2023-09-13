package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Inheritance( strategy = InheritanceType.JOINED )
@Table(schema="tedi", name="person")
@JsonIgnoreProperties(value = {"messagesSend","messagesRecieved","reservations","hostReviews"})
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String idNumber;
    private String email;
    private String phoneNumber;

//    @JsonIgnore
//    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    private User user;


    @JsonIgnore
    @OneToMany(mappedBy = "guest")
    private Set<Reservation> reservations;

    @JsonIgnore
    @OneToMany(mappedBy = "host")
    private Set<HostReview> hostReviews;

    @JsonIgnore
    @OneToMany(mappedBy = "reviewer")
    private Set<RoomReview> roomReviews;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private Set<Enrollment> enrollements;
}
