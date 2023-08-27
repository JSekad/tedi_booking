package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.RoomImageDefault;
import di.uoa.tedi_booking.services.RoomImageDefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/roomImageDefault")
public class RoomImageDefaultController extends GenericController<RoomImageDefault>{

    private final RoomImageDefaultService roomImageDefaultService;

    @Autowired
    public RoomImageDefaultController(RoomImageDefaultService service){
        super(service);
        this.roomImageDefaultService = service;
    }
}
