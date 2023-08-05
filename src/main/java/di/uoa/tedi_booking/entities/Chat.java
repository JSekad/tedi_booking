package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="chat")
public class Chat implements Serializable {

    @Id
    private Integer id;

    private String message;
    private OffsetDateTime sendTimestamp;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean readStatus;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSender")
    private Person sender;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReciever")
    private Person reciever;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReservation")
    private Reservation reservation;
}
