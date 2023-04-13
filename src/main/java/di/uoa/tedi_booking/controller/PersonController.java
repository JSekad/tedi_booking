package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Person;
import di.uoa.tedi_booking.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/person")
public class PersonController extends GenericController<Person>{

    @Autowired
    public PersonController(PersonRepository personRepository){super(personRepository);}
}
