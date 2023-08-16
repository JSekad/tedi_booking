package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.RoomImage;
import di.uoa.tedi_booking.repositories.RoomImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomImageService extends GenericService<RoomImage>{

    private final RoomImageRepository roomImageRepository;
    @Autowired
    public RoomImageService(RoomImageRepository repository){
        super(repository);
        this.roomImageRepository = repository;
    }
}
