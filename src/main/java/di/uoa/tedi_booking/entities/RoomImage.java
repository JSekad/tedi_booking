package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(schema="tedi", name="room_images")
@NamedQueries({
        @NamedQuery(name="findAllRoomImages", query="select ri from RoomImage ri where ri.room.id = :idRoom")
})
public class RoomImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_image_seq")
    @SequenceGenerator(name = "room_image_seq", sequenceName = "room_image_seq")
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoom")
    private Room room;

    private byte[] image;

    public void setImage(String data){
        this.image = data.getBytes();
    }

    public void setImageFromMulitpart(byte[] bytes){
        this.image = bytes;
    }
}
