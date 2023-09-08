package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Property;
import di.uoa.tedi_booking.entities.Room;
import di.uoa.tedi_booking.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/property")
public class PropertyController extends GenericController<Property>{

    private final PropertyService propertyService;
    @Autowired
    public PropertyController(PropertyService service ){
        super(service);
        this.propertyService = service;
    }

    @GetMapping(path = "/searchProperty/{idOwner}/{city}/{address}/{addressNumber}")
    public @ResponseBody Property searchProperty(@PathVariable Integer idOwner, @PathVariable String city, @PathVariable String address, @PathVariable String addressNumber){
        return propertyService.searchProperty(idOwner, city, address, addressNumber);
    }

    @PostMapping(path = "/newProperty")
    public ResponseEntity<?> add(@RequestBody Property property){
        service.add(property);
        property = propertyService.searchProperty(property.getOwner().getId(), property.getCity().getName(), property.getAddress(), property.getAddressNumber());
        return new ResponseEntity<>(property.getId(), HttpStatus.OK);
    }
}
