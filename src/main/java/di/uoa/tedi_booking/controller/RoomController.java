package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Room;
import di.uoa.tedi_booking.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping(path = "/searchAvailability/{area}/{startDate}/{endDate}/{numOfPersons}")
    public @ResponseBody List<Room> searchAvailableRooms(@PathVariable String area, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate, @PathVariable Integer numOfPersons){
        return roomService.searchAvailableRooms(area, startDate, endDate, numOfPersons);
    }

}
