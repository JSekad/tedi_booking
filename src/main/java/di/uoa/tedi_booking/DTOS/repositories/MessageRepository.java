package di.uoa.tedi_booking.DTOS.repositories;


import di.uoa.tedi_booking.entities.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends GenericRepository<Message>{
}