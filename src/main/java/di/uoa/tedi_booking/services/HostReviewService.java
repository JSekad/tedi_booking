package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.HostReview;
import di.uoa.tedi_booking.repositories.HostReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostReviewService extends GenericService<HostReview>{

    @Autowired
    public HostReviewService(HostReviewRepository hostReviewRepository){ super(hostReviewRepository); }
}
