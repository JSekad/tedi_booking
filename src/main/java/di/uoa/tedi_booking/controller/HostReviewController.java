package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.HostReview;
import di.uoa.tedi_booking.services.HostReviewService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
