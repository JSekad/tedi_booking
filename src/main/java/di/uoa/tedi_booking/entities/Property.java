package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(schema="tedi", name="property")
@NamedQueries({
        @NamedQuery(name="searchProperty", query="select p from Property p where p.owner.id = :idOwner and p.city.name = :city and p.address = :address and p.addressNumber = :addressNumber")
})
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPerson")
    private Person owner;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    private Set<Room> rooms;

    private String description;
    private Integer rating;
    private String address;
    private String addressNumber;
    private String accessInformation;

//    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCity")
    private City city;

    private BigDecimal longitude;
    private BigDecimal latitude;
}
