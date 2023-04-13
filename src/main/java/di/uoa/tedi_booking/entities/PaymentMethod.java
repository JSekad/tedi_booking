package di.uoa.tedi_booking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="paymentMethod")
public class PaymentMethod implements Serializable {

    @Id
    private Integer id;


}
