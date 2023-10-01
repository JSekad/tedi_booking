package di.uoa.tedi_booking.DTOS.repositories;

import di.uoa.tedi_booking.entities.Availability;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends GenericRepository<Availability>{

    @Query(name="findByIdRoom")
    Availability findByIdRoom(Integer idRoom);
}
