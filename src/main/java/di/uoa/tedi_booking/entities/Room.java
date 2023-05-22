package di.uoa.tedi_booking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="room")
@NamedQueries({
        @NamedQuery(name="room.search", query="select r from Room r")
})
public class Room implements Serializable {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idProperty")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "idRoomType")
    private RoomType roomType;

    private Integer basePricePerNight;
    private Integer extraPricePerPerson;
    private String description;
    private Integer numOfDoubleBeds;
    private Integer numOfSingleBeds;
    private Integer numOfBedRooms;
    private Integer numOfBathrooms;
    private Boolean hasTV;
    private Boolean hasPrivateBathroom;
    private Boolean hasAirCondition;
    private Boolean hasWifi;
    private Boolean hasKitchen;
    private Boolean hasJacuzzi;
    private Integer capacity;
    private Boolean petsAllowed;
    private Boolean hasParking;
    private Boolean hasElevator;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<RoomReview> roomReviews;
}