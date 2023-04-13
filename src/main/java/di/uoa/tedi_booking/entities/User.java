package di.uoa.tedi_booking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="user")
public class User implements Serializable{

    @Id
    @Column(name = "idPerson")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "idPerson")
    private Person person;

    private String userName;
    private String password;
}
