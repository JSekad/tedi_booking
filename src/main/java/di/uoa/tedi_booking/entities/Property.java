package di.uoa.tedi_booking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="property")
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPerson")
    private Person person;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPropertyType")
    private PropertyType propertyType;
    private String description;
    private Integer rating;
    private String address;
    private String addressNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCity")
    private City city;
    private String xLongitude;
    private String yLatitude;
}
