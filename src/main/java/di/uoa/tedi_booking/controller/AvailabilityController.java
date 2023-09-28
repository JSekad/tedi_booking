package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Availability;
import di.uoa.tedi_booking.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/availability")
public class AvailabilityController extends GenericController<Availability>{

    private final AvailabilityService availabilityService;

    @Autowired
    public AvailabilityController(AvailabilityService service){
        super(service);
        this.availabilityService = service;
    }

    @GetMapping(path="/findByIdRoom/{idRoom}")
    public @ResponseBody Availability findByIdRoom(@PathVariable Integer idRoom){
        return availabilityService.findByIdRoom(idRoom);
    }

}
