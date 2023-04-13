package di.uoa.tedi_booking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="paymentMethod")
public class PaymentMethod implements Serializable {

    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "paymentMethod", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;
}