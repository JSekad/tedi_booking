package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Reservation;
import di.uoa.tedi_booking.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController extends GenericController<Reservation>{

    private final ReservationService reservationService;
    @Autowired
    public ReservationController(ReservationService service){
        super(service);
        this.reservationService = service;
    }
}