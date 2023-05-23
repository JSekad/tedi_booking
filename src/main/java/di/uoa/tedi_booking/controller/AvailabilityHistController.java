package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.AvailabilityHist;
import di.uoa.tedi_booking.services.AvailabilityHistService;
import di.uoa.tedi_booking.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/availabilityHist")
public class AvailabilityHistController extends GenericController<AvailabilityHist> {

    private final AvailabilityHistService availabilityHistService;
    @Autowired
    public AvailabilityHistController(AvailabilityHistService service){
        super(service);
        this.availabilityHistService = service;
    }
}
