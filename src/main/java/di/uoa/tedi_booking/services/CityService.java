package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.City;
import di.uoa.tedi_booking.DTOS.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService extends GenericService<City>{

    private final CityRepository cityRepository;
    @Autowired
    public CityService(CityRepository repository){
        super(repository);
        this.cityRepository = repository;
    }
}
