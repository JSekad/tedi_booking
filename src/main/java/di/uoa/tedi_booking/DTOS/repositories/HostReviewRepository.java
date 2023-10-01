package di.uoa.tedi_booking.DTOS.repositories;

import di.uoa.tedi_booking.entities.HostReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HostReviewRepository extends GenericRepository<HostReview> {

    @Query(name = "findByIdHost")
    HostReview findByIdHost(int idReviewer, int idHost);
}
