package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Property;
import di.uoa.tedi_booking.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/property")
public class PropertyController extends GenericController<Property>{

    @Autowired
    public PropertyController(PropertyService propertyService){ super(propertyService); }

}
