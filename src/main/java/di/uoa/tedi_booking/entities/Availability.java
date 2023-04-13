package di.uoa.tedi_booking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="availability")
public class Availability implements Serializable {

    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoom")
    private Room room;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Integer discount;
}
