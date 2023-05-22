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

    @Autowired
    public RoomService(RoomRepository roomRepository){ super(roomRepository); }

    public List<Room> searchRoom(String area, LocalDate startDate, LocalDate endDate, Long numPersons){
        return null;
    }

}
