package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Country;
import di.uoa.tedi_booking.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/country")
public class CountryController extends GenericController<Country>{

    @Autowired
    public CountryController(CountryRepository countryRepository){super(countryRepository);}
}