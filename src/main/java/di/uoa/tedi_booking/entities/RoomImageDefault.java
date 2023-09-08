package di.uoa.tedi_booking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(schema="tedi", name="room_image_default")
public class RoomImageDefault implements Serializable {
    @Id
    @Column(name="idRoom")
    private Integer id;

    private byte[] image;

    public void setImage(String data){
        this.image = data.getBytes();
    }

    public void setImageFromMulitpart(byte[] bytes){
        this.image = bytes;
    }
}