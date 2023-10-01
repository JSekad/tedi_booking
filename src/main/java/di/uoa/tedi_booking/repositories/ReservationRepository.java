package di.uoa.tedi_booking.repositories;

import di.uoa.tedi_booking.entities.Reservation;
import di.uoa.tedi_booking.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends GenericRepository<Reservation> {

    @Query(name = "findByIdUser")
    List<Reservation> findReservationsByIdGuest(int idGuest);
}
