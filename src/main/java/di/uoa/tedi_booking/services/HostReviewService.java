package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.HostReview;
import di.uoa.tedi_booking.DTOS.repositories.HostReviewRepository;
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

    public HostReview findByIdHost(int idReviewer, int idHost){
        return this.hostReviewRepository.findByIdHost(idReviewer, idHost);
    }
}
