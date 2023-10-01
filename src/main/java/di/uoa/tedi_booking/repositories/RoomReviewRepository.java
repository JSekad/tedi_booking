package di.uoa.tedi_booking.repositories;

import di.uoa.tedi_booking.entities.Reservation;
import di.uoa.tedi_booking.entities.RoomReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomReviewRepository extends GenericRepository<RoomReview>{

    @Query(name = "findReviewByIdRoom")
    RoomReview findReviewByIdRoom(int idReviewer, int idRoom);
}
