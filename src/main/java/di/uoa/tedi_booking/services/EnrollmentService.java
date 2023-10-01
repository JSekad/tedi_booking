package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Enrollment;
import di.uoa.tedi_booking.DTOS.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService extends GenericService<Enrollment>{

    private final EnrollmentRepository enrollmentRepository;
    @Autowired
    public EnrollmentService(EnrollmentRepository repository){
        super(repository);
        this.enrollmentRepository = repository;
    }
}
