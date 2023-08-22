package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.RoomType;
import di.uoa.tedi_booking.repositories.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService extends GenericService<RoomType>{

    private final RoomTypeRepository roomTypeRepository;
    @Autowired
    public RoomTypeService(RoomTypeRepository repository){
        super(repository);
        this.roomTypeRepository = repository;
    }
}
