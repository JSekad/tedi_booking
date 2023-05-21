package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Person;
import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.repositories.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends GenericService<Person> {


    public PersonService(GenericRepository<Person> repository) {
        super(repository);
    }

}