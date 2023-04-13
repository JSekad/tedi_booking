package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Availability;
import di.uoa.tedi_booking.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/availability")
public class AvailabilityController extends GenericController<Availability>{

    @Autowired
    public AvailabilityController(AvailabilityRepository availabilityRepository){super(availabilityRepository);}
}
