package di.uoa.tedi_booking.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(schema="tedi", name="hostphoto")
public class HostPhoto implements Serializable {

    @Id
    @Column(name="idPerson")
    private Long id;

    private byte[] photo;

    public void setImageFromMulitpart(byte[] bytes){
        this.photo = bytes;
    }
}