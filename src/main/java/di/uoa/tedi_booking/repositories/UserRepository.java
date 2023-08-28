package di.uoa.tedi_booking.repositories;

import di.uoa.tedi_booking.entities.PropertyType;
import di.uoa.tedi_booking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User> {
    Optional<User> findAllByUserName(String userName);

    @Query(name = "usersMeAitimaEggrafis")
    List<User> usersMeAitimaEggrafis();
}
