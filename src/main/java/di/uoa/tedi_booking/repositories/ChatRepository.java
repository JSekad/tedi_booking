package di.uoa.tedi_booking.repositories;

import di.uoa.tedi_booking.DTOS.ChatDTO;
import di.uoa.tedi_booking.entities.Chat;
import di.uoa.tedi_booking.entities.Room;
import di.uoa.tedi_booking.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface ChatRepository extends GenericRepository<Chat>{

    HashSet<Chat> getChatByFirstUser(User firstUserName);

    HashSet<Chat> getChatBySecondUser(User secondUserName);

    Chat getChatById(Long id);

    HashSet<Chat> getChatByFirstUserAndSecondUser(User firstUser, User secondUser);

    HashSet<Chat> getChatBySecondUserAndFirstUser(User firstUser, User secondUser);

    HashSet<Chat> getChatByFirstUserAndSecondUserAndConversationForRoom(User firstUser, User secondUser, Room room);

    HashSet<Chat> getChatBySecondUserAndFirstUserAndConversationForRoom(User firstUser, User secondUser, Room room);

}
