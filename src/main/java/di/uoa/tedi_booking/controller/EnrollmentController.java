package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Enrollment;
import di.uoa.tedi_booking.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/enrollment")
public class EnrollmentController extends GenericController<Enrollment> {

    @Autowired
    public EnrollmentController(EnrollmentRepository enrollmentRepository){super(enrollmentRepository);}
}
