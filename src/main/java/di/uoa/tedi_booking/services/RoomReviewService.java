package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.RoomReview;
import di.uoa.tedi_booking.DTOS.repositories.RoomReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomReviewService extends GenericService<RoomReview>{

    private final RoomReviewRepository roomReviewRepository;
    @Autowired
    public RoomReviewService(RoomReviewRepository repository){
        super(repository);
        this.roomReviewRepository = repository;
    }

    public RoomReview findReviewByIdRoom(int idReviewer, int idRoom){
        return this.roomReviewRepository.findReviewByIdRoom(idReviewer, idRoom);
    }
}
