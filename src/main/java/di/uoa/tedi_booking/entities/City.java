package di.uoa.tedi_booking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
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
    private Integer id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCountry")
    private Country country;
    private Integer postCode;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Set<Property> properties;
}
