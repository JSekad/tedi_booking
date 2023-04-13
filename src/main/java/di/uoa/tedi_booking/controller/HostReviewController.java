package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.HostReview;
import di.uoa.tedi_booking.repositories.HostReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/hostReview")
public class HostReviewController extends GenericController<HostReview>{

    @Autowired
    public HostReviewController(HostReviewRepository hostReviewRepository){super(hostReviewRepository);}
}
