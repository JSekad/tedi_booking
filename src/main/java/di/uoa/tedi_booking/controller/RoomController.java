package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Room;
import di.uoa.tedi_booking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/room")
public class RoomController extends GenericController<Room>{

    @Autowired
    public RoomController(RoomRepository roomRepository){super(roomRepository);}
}
