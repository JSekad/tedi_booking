package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Enrollment;
import di.uoa.tedi_booking.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/enrollment")
public class EnrollmentController extends GenericController<Enrollment> {

    private final EnrollmentService enrollmentService;
    @Autowired
    public EnrollmentController(EnrollmentService service){
        super(service);
        this.enrollmentService = service;
    }
}
