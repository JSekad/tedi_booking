package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.AvailabilityHist;
import di.uoa.tedi_booking.repositories.AvailabilityHistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityHistService extends GenericService<AvailabilityHist>{

    @Autowired
    public AvailabilityHistService(AvailabilityHistRepository availabilityHistRepository){ super(availabilityHistRepository); }
}
