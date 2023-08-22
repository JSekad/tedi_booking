package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.RoomType;
import di.uoa.tedi_booking.services.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/roomType")
public class RoomTypeController extends GenericController<RoomType>{

    private final RoomTypeService roomTypeService;

    @Autowired
    public RoomTypeController(RoomTypeService service){
        super(service);
        this.roomTypeService = service;
    }
}
