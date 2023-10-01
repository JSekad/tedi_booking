package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.RoomReview;
import di.uoa.tedi_booking.services.RoomReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/roomReview")
public class RoomReviewController extends GenericController<RoomReview>{

    private final RoomReviewService roomReviewService;
    @Autowired
    public RoomReviewController(RoomReviewService service){
        super(service);
        this.roomReviewService = service;
    }

    @GetMapping(path = "/findForThisRoom/{idReviewer}/{idRoom}")
    public ResponseEntity<?> findByIdRoom(@PathVariable String idReviewer, @PathVariable String idRoom){
        if(this.roomReviewService.findReviewByIdRoom(Integer.parseInt(idReviewer), Integer.parseInt(idRoom)) == null)
            return new ResponseEntity<>("null", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>("found", HttpStatus.OK);
    }
}
