package di.uoa.tedi_booking.repositories;

import di.uoa.tedi_booking.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {
}
