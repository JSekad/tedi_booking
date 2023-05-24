package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="hostReview")
public class HostReview implements Serializable {

    @Id
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHost")
    private Person host;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReviewer")
    private Person reviewer;

    private String title;
    private String review;
    private Integer rating;
    private OffsetDateTime sendTimestamp;
}
