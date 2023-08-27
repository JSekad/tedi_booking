package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(schema="booking_app", name="person")
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
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean approved;
    private OffsetDateTime dateApproved;

    @JsonIgnore
    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private Set<Chat> messagesSend;

    @JsonIgnore
    @OneToMany(mappedBy = "reciever", fetch = FetchType.LAZY)
    private Set<Chat> messagesRecieved;

    @JsonIgnore
    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @JsonIgnore
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    private Set<HostReview> hostReviews;

    @JsonIgnore
    @OneToMany(mappedBy = "reviewer", fetch = FetchType.LAZY)
    private Set<RoomReview> roomReviews;

    @JsonIgnore
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<Enrollment> enrollements;
}
