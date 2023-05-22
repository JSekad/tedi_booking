package di.uoa.tedi_booking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="roomType")
public class RoomType implements Serializable {

    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "roomType", fetch = FetchType.LAZY)
    private Set<Room> rooms;
}
