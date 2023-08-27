package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(schema="tedi", name="room")
@NamedQueries({
        @NamedQuery(name="searchAvailableRooms", query="select distinct(r) " +
            " from Room r left outer join Reservation res on res.room.id = r.id inner join Availability a on a.room.id = r.id " +
            " where r.property.city.name = :city and r.capacity >= :numOfPersons and a.startDate <= :startDate and a.endDate >= :endDate " +
            " and (res is null or not(r.id = any (select rr.room.id from Reservation rr where r.id = rr.room.id and (:startDate >= rr.startDate and :startDate <= rr.endDate) or (:endDate >= rr.startDate and :endDate <= rr.endDate) " +
                " or (:startDate <= rr.startDate and :endDate >= rr.endDate) ))) order by r.basePricePerNight")
})
public class Room implements Serializable {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idProperty")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "idRoomType")
    private RoomType type;

    private Integer basePricePerNight;
    private Integer extraPricePerPerson;
    private String description;
    private Integer numOfDoubleBeds;
    private Integer numOfSingleBeds;
    private Integer numOfBedrooms;
    private Integer numOfBathrooms;
    private Integer minRentDays;
    private Boolean hasTV;
    private Boolean hasPrivateBathroom;
    private Boolean hasAirCondition;
    private Boolean hasWifi;
    private Boolean hasKitchen;
    private Boolean hasJacuzzi;
    private Integer areaSize;
    private Integer capacity;
    private Boolean petsAllowed;
    private Boolean hasParking;
    private Boolean hasElevator;
    private Boolean smokingAllowed;
    private Boolean partyAllowed;
    private Integer numOfReviews;
    private Float averageReviews;

    @JsonIgnore
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @JsonIgnore
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<RoomReview> roomReviews;

    @JsonIgnore
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<RoomImage> roomImages;

    @OneToOne
    @JoinColumn(name="id", referencedColumnName = "idRoom")
    private RoomImageDefault defaultRoomImage;

    public Room(){}

    public Room(Integer id, Integer basePricePerNight){
        this.id = id;
        this.basePricePerNight = basePricePerNight;
    }
}