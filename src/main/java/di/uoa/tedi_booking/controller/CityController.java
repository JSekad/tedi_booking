package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.City;
import di.uoa.tedi_booking.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/city")
public class CityController extends GenericController<City>{

    private final CityService cityService;
    @Autowired
    public CityController(CityService service){
        super(service);
        this.cityService = service;
    }
}
