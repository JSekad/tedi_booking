package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(schema="tedi", name="roomReview")
@NamedQueries({
        @NamedQuery(name="findReviewByIdRoom", query="select r from RoomReview r where r.reviewer.id = :idReviewer and r.room.id = :idRoom")
})
public class RoomReview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomreview_seq")
    @SequenceGenerator(name = "roomreview_seq", sequenceName = "roomreview_seq")
    private Integer id;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoom")
    private Room room;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReviewer")
    private Person reviewer;

    private String title;
    private String review;
    private Integer rating;
    private OffsetDateTime sendTimestamp;
}
