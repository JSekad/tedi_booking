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
@Table(schema="booking_app", name="room")
@NamedQueries({
        @NamedQuery(name="searchAvailableRooms", query="select r from Room r left outer join Reservation res on res.room.id = r.id inner join Availability a on a.room.id = r.id " +
            "where r.property.city.name = :city and (res.startDate >= :startDate and res.startDate >= :endDate or res.startDate is null) or (res.endDate <= :endDate " +
            "and res.endDate <= :endDate or res.endDate is null) and r.capacity <= :numOfPersons and a.startDate <= :startDate and a.endDate >= :endDate")
})

public class Room implements Serializable {

    @Id
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idProperty")
    private Property property;

    @JsonIgnore
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

    @JsonIgnore
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @JsonIgnore
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<RoomReview> roomReviews;
}