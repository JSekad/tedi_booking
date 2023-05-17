package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.PropertyType;
import di.uoa.tedi_booking.repositories.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/propertyType")
public class PropertyTypeController extends GenericController<PropertyType>{
    @Autowired
    public PropertyTypeController(PropertyTypeRepository propertyTypeRepository){super(propertyTypeRepository);}
}
