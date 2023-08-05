package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.PropertyType;
import di.uoa.tedi_booking.repositories.PropertyRepository;
import di.uoa.tedi_booking.repositories.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyTypeService extends GenericService<PropertyType>{

    private final PropertyTypeRepository propertyTypeRepository;

    @Autowired
    public PropertyTypeService(PropertyTypeRepository repository){
        super(repository);
        this.propertyTypeRepository = repository;
    }
}
