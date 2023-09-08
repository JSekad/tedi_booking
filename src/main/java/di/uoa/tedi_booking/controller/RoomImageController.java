package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Room;
import di.uoa.tedi_booking.entities.RoomImage;
import di.uoa.tedi_booking.entities.RoomImageDefault;
import di.uoa.tedi_booking.services.RoomImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping(path = "/newImage")
    public ResponseEntity<?> newImage(@RequestParam(value = "idRoom") String id, @RequestParam(value ="image") MultipartFile img) throws IOException {
        RoomImage newImage = new RoomImage();
        Room tempRoom = new Room();

        tempRoom.setId(Integer.parseInt(id));
        newImage.setRoom(tempRoom);
        newImage.setImageFromMulitpart(img.getBytes());
        return this.service.add(newImage);
    }
}