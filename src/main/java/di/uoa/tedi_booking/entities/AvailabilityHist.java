package di.uoa.tedi_booking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="availabilityHist")
public class AvailabilityHist implements Serializable {

    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoom")
    private Room room;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer discount;
}
