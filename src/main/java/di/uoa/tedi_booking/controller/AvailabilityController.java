package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Availability;
import di.uoa.tedi_booking.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/availability")
public class AvailabilityController extends GenericController<Availability>{

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService){ super(availabilityService); }
}
