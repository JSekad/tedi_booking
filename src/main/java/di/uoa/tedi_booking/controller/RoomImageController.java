package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.RoomImage;
import di.uoa.tedi_booking.services.RoomImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/roomImage")
public class RoomImageController extends GenericController<RoomImage> {

    private final RoomImageService roomImageService;

    @Autowired
    public RoomImageController(RoomImageService service){
        super(service);
        this.roomImageService = service;
    }
}
