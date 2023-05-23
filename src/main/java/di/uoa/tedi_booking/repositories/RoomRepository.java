package di.uoa.tedi_booking.repositories;

import di.uoa.tedi_booking.entities.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends GenericRepository<Room>{

    @Query(name = "Room.searchAvailableRooms")
    List<Room> searchAvailableRooms(String area, LocalDate startDate, LocalDate endDate, Long numOfPersons);
}