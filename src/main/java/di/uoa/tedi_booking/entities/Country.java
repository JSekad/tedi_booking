package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="country")
public class Country implements Serializable {

    @Id
    private Integer id;

    private String name;
    private String alias;

    @JsonIgnore
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<City> cities;

}
