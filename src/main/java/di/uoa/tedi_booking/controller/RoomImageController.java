package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.RoomImage;
import di.uoa.tedi_booking.services.RoomImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/roomImage")
public class RoomImageController extends GenericController<RoomImage> {

    private final RoomImageService roomImageService;

    @Autowired
    public RoomImageController(RoomImageService service){
        super(service);
        this.roomImageService = service;
    }

    @GetMapping(path = "/roomImages/{idRoom}")
    public @ResponseBody List<RoomImage> findAllRoomImages(@PathVariable Integer idRoom){
        return roomImageService.findAllRoomImages(idRoom);
    }
}