package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(schema="tedi", name="reservation")
@NamedQueries({
        @NamedQuery(name="findByIdUser", query="select r from Reservation r where r.guest.id = :idGuest")
})
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    @SequenceGenerator(name = "reservation_seq", sequenceName = "reservation_seq")
    private Integer id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idRoom")
    private Room room;

//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idGuest")
    private Person guest;

    private Integer numOfPersons;
    private Integer price;
    private OffsetDateTime reservationTimestamp;
    private OffsetDateTime cancelationTimestamp;

    private LocalDate startDate;
    private LocalDate endDate;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHostReview")
    private HostReview hostReview;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoomReview")
    private RoomReview RoomReview;
}
