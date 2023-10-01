package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Person;
import di.uoa.tedi_booking.DTOS.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends GenericService<Person> {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository repository) {
        super(repository);
        this.personRepository = repository;
    }

}