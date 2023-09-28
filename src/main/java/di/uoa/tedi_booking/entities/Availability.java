package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(schema="tedi", name="availability")
@NamedQueries({
        @NamedQuery(name="findByIdRoom", query="select a from Availability a where a.room.id = :idRoom")
})
public class Availability implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "availability_seq")
    @SequenceGenerator(name = "availability_seq", sequenceName = "availability_seq")
    private Integer id;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoom")
    private Room room;

    private LocalDate startDate;

    private LocalDate endDate;

    @JsonIgnore
    private Integer discount;
}
