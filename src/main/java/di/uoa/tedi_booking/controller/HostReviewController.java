package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.HostReview;
import di.uoa.tedi_booking.services.HostReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hostReview")
public class HostReviewController extends GenericController<HostReview>{

    private final HostReviewService hostReviewService;
    @Autowired
    public HostReviewController(HostReviewService service){
        super(service);
        this.hostReviewService = service;
    }

    @GetMapping(path = "/findForThisHost/{idReviewer}/{idHost}")
    public ResponseEntity<?> findByIdHost(@PathVariable String idReviewer, @PathVariable String idHost){
        if(this.hostReviewService.findByIdHost(Integer.parseInt(idReviewer), Integer.parseInt(idHost)) == null)
            return new ResponseEntity<>("null", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>("found", HttpStatus.OK);
    }
}
