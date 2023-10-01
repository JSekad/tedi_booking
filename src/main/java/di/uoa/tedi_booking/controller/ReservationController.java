package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Reservation;
import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController extends GenericController<Reservation>{

    private final ReservationService reservationService;
    @Autowired
    public ReservationController(ReservationService service){
        super(service);
        this.reservationService = service;
    }

    @GetMapping(path = "/findReservationsByIdGuest/{idGuest}")
    public @ResponseBody List<Reservation> findReservationsByIdGuest(@PathVariable String idGuest){
        return this.reservationService.findReservationsByIdGuest(Integer.parseInt(idGuest));
    }
}