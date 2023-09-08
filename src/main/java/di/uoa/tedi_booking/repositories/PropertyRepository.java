package di.uoa.tedi_booking.repositories;

import di.uoa.tedi_booking.entities.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends GenericRepository<Property> {

    @Query(name="searchProperty")
    Property searchProperty(Integer idOwner, String city, String address, String addressNumber);
}
