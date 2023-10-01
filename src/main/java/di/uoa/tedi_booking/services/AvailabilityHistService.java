package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.AvailabilityHist;
import di.uoa.tedi_booking.DTOS.repositories.AvailabilityHistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityHistService extends GenericService<AvailabilityHist>{

    private final AvailabilityHistRepository availabilityHistRepository;

    @Autowired
    public AvailabilityHistService(AvailabilityHistRepository repository){
        super(repository);
        this.availabilityHistRepository = repository;
    }
}
