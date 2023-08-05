package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Reservation;
import di.uoa.tedi_booking.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService extends GenericService<Reservation>{

    private final ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(ReservationRepository repository){
        super(repository);
        this.reservationRepository = repository;
    }
}
