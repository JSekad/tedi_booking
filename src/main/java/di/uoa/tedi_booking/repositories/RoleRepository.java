package di.uoa.tedi_booking.repositories;

import di.uoa.tedi_booking.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long > {
    Role findById(int id);
}