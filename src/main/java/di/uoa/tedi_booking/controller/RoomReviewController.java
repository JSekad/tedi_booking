package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Room;
import di.uoa.tedi_booking.entities.RoomReview;
import di.uoa.tedi_booking.services.RoomReviewService;
import di.uoa.tedi_booking.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/roomReview")
public class RoomReviewController extends GenericController<RoomReview>{

    private final RoomReviewService roomReviewService;
    private final RoomService roomService;
    @Autowired
    public RoomReviewController(RoomReviewService service, RoomService roomServ){
        super(service);
        this.roomReviewService = service;
        this.roomService = roomServ;
    }

    @GetMapping(path = "/findForThisRoom/{idReviewer}/{idRoom}")
    public ResponseEntity<?> findByIdRoom(@PathVariable String idReviewer, @PathVariable String idRoom){
        if(this.roomReviewService.findReviewByIdRoom(Integer.parseInt(idReviewer), Integer.parseInt(idRoom)) == null)
            return new ResponseEntity<>("null", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>("found", HttpStatus.OK);
    }

    @PostMapping(path = "/addRoomReview")
    public ResponseEntity<?> addRoomReview(@RequestBody RoomReview review){
        //update rooms average rating
//        Room room = this.roomService.find(review.getRoom().getId().longValue());
        Room room = review.getRoom();
        int numOfReviews = room.getNumOfReviews() + 1;
        room.setNumOfReviews(numOfReviews);
        room.setAverageReviews(room.getAverageReviews() + ((float)review.getRating() - room.getAverageReviews()) / (float)numOfReviews);
        this.roomService.update(room);

        //add new review
        return this.service.add(review);
    }
}
