package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.HostReview;
import di.uoa.tedi_booking.entities.Person;
import di.uoa.tedi_booking.entities.Room;
import di.uoa.tedi_booking.entities.RoomReview;
import di.uoa.tedi_booking.services.HostReviewService;
import di.uoa.tedi_booking.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/hostReview")
public class HostReviewController extends GenericController<HostReview>{

    private final HostReviewService hostReviewService;
    private final PersonService personService;

    @Autowired
    public HostReviewController(HostReviewService service, PersonService personService){
        super(service);
        this.hostReviewService = service;
        this.personService = personService;
    }

    @GetMapping(path = "/findForThisHost/{idReviewer}/{idHost}")
    public ResponseEntity<?> findByIdHost(@PathVariable String idReviewer, @PathVariable String idHost){
        if(this.hostReviewService.findByIdHost(Integer.parseInt(idReviewer), Integer.parseInt(idHost)) == null)
            return new ResponseEntity<>("null", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>("found", HttpStatus.OK);
    }

    @PostMapping(path = "/addHostReview")
    public ResponseEntity<?> addHostReview(@RequestBody HostReview review){
        //update host's average rating
//        Person host = review.getHost();
        Person host = this.personService.find(review.getHost().getId().longValue());
        int numOfReviews = host.getNumOfReviews() + 1;
        host.setNumOfReviews(numOfReviews);
        host.setAverageReviews(host.getAverageReviews() + ((float)review.getRating() - host.getAverageReviews()) / (float)numOfReviews);
        this.personService.update(host);

        //add new review
        return this.service.add(review);
    }
}
