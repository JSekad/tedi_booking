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
@Table(schema="booking_app", name="property")
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPerson")
    private Person person;

    @JsonIgnore
    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    private Set<Room> rooms;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPropertyType")
    private PropertyType propertyType;
    private String description;
    private Integer rating;
    private String address;
    private String addressNumber;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCity")
    private City city;
    private String xLongitude;
    private String yLatitude;
}
