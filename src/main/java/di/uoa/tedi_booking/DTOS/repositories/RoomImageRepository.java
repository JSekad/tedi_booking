package di.uoa.tedi_booking.DTOS.repositories;

import di.uoa.tedi_booking.entities.RoomImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomImageRepository extends GenericRepository<RoomImage>{

    @Query(name = "findAllRoomImages")
    List<RoomImage> findAllRoomImages(Integer idRoom);
}
