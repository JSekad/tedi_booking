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
@Table(schema="tedi", name="propertyType")
public class PropertyType implements Serializable {

    @Id
    private Integer id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "propertyType", fetch = FetchType.LAZY)
    private Set<Property> properties;
}
