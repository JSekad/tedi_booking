package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Enrollment;
import di.uoa.tedi_booking.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService extends GenericService<Enrollment>{

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository){ super(enrollmentRepository); }
}
