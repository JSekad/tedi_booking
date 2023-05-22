package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.PropertyType;
import di.uoa.tedi_booking.repositories.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyTypeService extends GenericService<PropertyType>{

    @Autowired
    public PropertyTypeService(PropertyTypeRepository propertyTypeRepository){ super(propertyTypeRepository); }
}
