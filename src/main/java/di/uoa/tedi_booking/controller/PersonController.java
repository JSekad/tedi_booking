package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Person;
import di.uoa.tedi_booking.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/person")
public class PersonController extends GenericController<Person>{

    private final PersonService personService;
    @Autowired
    public PersonController(PersonService service){
        super(service);
        this.personService = service;
    }
}
