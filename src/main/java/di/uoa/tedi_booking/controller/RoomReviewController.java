package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.RoomReview;
import di.uoa.tedi_booking.repositories.RoomReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/roomReview")
public class RoomReviewController extends GenericController<RoomReview>{

    @Autowired
    public RoomReviewController(RoomReviewRepository roomReviewRepository){super(roomReviewRepository);}
}
