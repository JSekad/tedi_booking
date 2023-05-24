package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="reservation")
public class Reservation implements Serializable {

    @Id
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoom")
    private Room room;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
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

    private String specialRequest;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPaymentMethod")
    private PaymentMethod paymentMethod;
}
