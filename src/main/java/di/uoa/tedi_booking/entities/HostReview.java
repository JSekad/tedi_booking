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
@Table(schema="tedi", name="hostReview")
@NamedQueries({
        @NamedQuery(name="findByIdHost", query="select r from HostReview r where r.reviewer.id = :idReviewer and r.host.id = :idHost")
})
public class HostReview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hostreview_seq")
    @SequenceGenerator(name = "hostreview_seq", sequenceName = "hostreview_seq")
    private Integer id;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHost")
    private Person host;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReviewer")
    private Person reviewer;

    private String title;
    private String review;
    private Integer rating;
    private OffsetDateTime sendTimestamp;
}
