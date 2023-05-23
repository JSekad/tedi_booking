package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Room;
import di.uoa.tedi_booking.repositories.GenericRepository;
import di.uoa.tedi_booking.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService extends GenericService<Room>{

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository repository){
        super(repository);
        this.roomRepository= repository;
    }

    public List<Room> searchAvailableRooms(String area, LocalDate startDate, LocalDate endDate, Long numPersons){
        return roomRepository.searchAvailableRooms(area, startDate, endDate, numPersons);
    }

}
