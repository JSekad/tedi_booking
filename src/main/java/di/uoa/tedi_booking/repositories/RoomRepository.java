package di.uoa.tedi_booking.repositories;

import di.uoa.tedi_booking.entities.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends GenericRepository<Room>{

    @Query(name = "searchAvailableRooms")
    List<Room> searchAvailableRooms(String city, LocalDate startDate, LocalDate endDate, Integer numOfPersons);
}