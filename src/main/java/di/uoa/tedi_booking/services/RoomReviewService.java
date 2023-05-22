package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.RoomReview;
import di.uoa.tedi_booking.repositories.RoomReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomReviewService extends GenericService<RoomReview>{

    @Autowired
    public RoomReviewService(RoomReviewRepository roomReviewRepository){ super(roomReviewRepository); }
}
