package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.HostPhoto;
import di.uoa.tedi_booking.services.HostPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hostPhoto")
public class HostPhotoController extends GenericController<HostPhoto>{

    private final HostPhotoService hostPhotoService;

    @Autowired
    public HostPhotoController(HostPhotoService service){
        super(service);
        this.hostPhotoService = service;
    }
}
