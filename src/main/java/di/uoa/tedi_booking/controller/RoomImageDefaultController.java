package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.RoomImageDefault;
import di.uoa.tedi_booking.services.RoomImageDefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping(path = "/roomImageDefault")
public class RoomImageDefaultController extends GenericController<RoomImageDefault>{

    private final RoomImageDefaultService roomImageDefaultService;

    @Autowired
    public RoomImageDefaultController(RoomImageDefaultService service){
        super(service);
        this.roomImageDefaultService = service;
    }

    @PostMapping(path = "/newDefaultImage")
    public ResponseEntity<?> newDefaultImage(@RequestParam(value = "idRoom") String id, @RequestParam(value ="image") MultipartFile img) throws IOException {
       RoomImageDefault newImage = new RoomImageDefault();
       newImage.setId(Integer.parseInt(id));
       newImage.setImageFromMulitpart(img.getBytes());
       return this.service.add(newImage);
    }

    @PostMapping(path = "/updateDefaultImage")
    public ResponseEntity<?> updateDefaultImage(@RequestParam(value = "idRoom") String id, @RequestParam(value ="image") MultipartFile img) throws IOException {
        RoomImageDefault newImage = new RoomImageDefault();
        newImage.setId(Integer.parseInt(id));
        newImage.setImageFromMulitpart(img.getBytes());
        return this.service.update(newImage);
    }
}
