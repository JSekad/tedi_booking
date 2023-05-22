package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Property;
import di.uoa.tedi_booking.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService extends GenericService<Property>{

    @Autowired
    public PropertyService(PropertyRepository propertyRepository){ super(propertyRepository); }
}
