package di.uoa.tedi_booking.repositories;

import di.uoa.tedi_booking.entities.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends GenericRepository<Reservation> {
}
