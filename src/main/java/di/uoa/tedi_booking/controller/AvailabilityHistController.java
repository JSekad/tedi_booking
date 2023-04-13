package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.AvailabilityHist;
import di.uoa.tedi_booking.repositories.AvailabilityHistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/availabilityHist")
public class AvailabilityHistController extends GenericController<AvailabilityHist> {

    @Autowired
    public AvailabilityHistController(AvailabilityHistRepository availabilityHistRepository){super(availabilityHistRepository);}
}
