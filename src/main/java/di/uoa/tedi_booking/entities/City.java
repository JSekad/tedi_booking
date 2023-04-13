package di.uoa.tedi_booking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="city")
public class City implements Serializable {

    @Id
    private Integer city;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCountry")
    private Country country;
    private Integer postCode;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Set<Property> properties;
}
