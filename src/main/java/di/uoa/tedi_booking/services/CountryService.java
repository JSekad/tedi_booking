package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Country;
import di.uoa.tedi_booking.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends GenericService<Country>{

    private final CountryRepository countryRepository;
    @Autowired
    public CountryService(CountryRepository repository){
        super(repository);
        this.countryRepository = repository;
    }
}
