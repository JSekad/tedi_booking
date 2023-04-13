package di.uoa.tedi_booking.entities;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoom")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGuest")
    private Person guest;

    private Integer numOfPersons;
    private Integer price;
    private OffsetDateTime reservationTimestamp;
    private OffsetDateTime cancelationTimestamp;

    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne(fetch = FetchType.LAZY)
    private HostReview hostReview;
    @OneToOne(fetch = FetchType.LAZY)
    private RoomReview RoomReview;

    private String speicalRequest;
    private PaymentMethod paymentMethod;
}
