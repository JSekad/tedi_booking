package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.RoomImageDefault;
import di.uoa.tedi_booking.DTOS.repositories.RoomImageDefaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomImageDefaultService extends GenericService<RoomImageDefault> {
    private final RoomImageDefaultRepository roomImageDefaultRepository;

    @Autowired
    public RoomImageDefaultService(RoomImageDefaultRepository repository){
        super(repository);
        this.roomImageDefaultRepository = repository;
    }
}
