package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Room;
import di.uoa.tedi_booking.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(path = "/room")
public class RoomController extends GenericController<Room>{

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService service){
        super(service);
        this.roomService = service;
    }

    @GetMapping(path = "/searchAvailableRooms/{city}/{startDate}/{endDate}/{numOfPersons}")
    public @ResponseBody List<Room> searchAvailableRooms(@PathVariable String city, @PathVariable String startDate, @PathVariable String endDate, @PathVariable Integer numOfPersons){
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return roomService.searchAvailableRooms(city, LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter), numOfPersons);
    }

}
