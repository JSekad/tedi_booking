package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.HostReview;
import di.uoa.tedi_booking.repositories.HostReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostReviewService extends GenericService<HostReview>{

    private final HostReviewRepository hostReviewRepository;
    @Autowired
    public HostReviewService(HostReviewRepository repository){
        super(repository);
        this.hostReviewRepository = repository;
    }
}
