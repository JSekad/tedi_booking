package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Property;
import di.uoa.tedi_booking.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/property")
public class PropertyController extends GenericController<Property>{

    @Autowired
    public PropertyController(PropertyRepository propertyRepository){super(propertyRepository);}

}
