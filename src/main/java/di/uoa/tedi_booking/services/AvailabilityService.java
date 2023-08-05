package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Availability;
import di.uoa.tedi_booking.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityService extends GenericService<Availability>{

    private final AvailabilityRepository availabilityRepository;

    @Autowired
    public AvailabilityService(AvailabilityRepository repository){
        super(repository);
        this.availabilityRepository = repository;
    }
}
