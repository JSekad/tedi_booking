package di.uoa.tedi_booking.DTOS.repositories;

import di.uoa.tedi_booking.entities.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends GenericRepository<Reservation> {

    @Query(name = "findByIdUser")
    List<Reservation> findReservationsByIdGuest(int idGuest);
}
