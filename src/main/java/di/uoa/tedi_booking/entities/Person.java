package di.uoa.tedi_booking.entities;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String surname;
    private String fathersName;
    private String mothersName;
    private LocalDate birthDate;
    private String idNumber;
    private String email;
    private String phoneNumber;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean approved;
    private OffsetDateTime dateApproved;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private Set<Chat> messagesSend;

    @OneToMany(mappedBy = "reciever", fetch = FetchType.LAZY)
    private Set<Chat> messagesRecieved;

    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    private Set<HostReview> hostReviews;

    @OneToMany(mappedBy = "reviewer", fetch = FetchType.LAZY)
    private Set<RoomReview> roomReviews;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<Enrollment> enrollements;
}
