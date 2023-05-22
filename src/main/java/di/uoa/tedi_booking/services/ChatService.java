package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Chat;
import di.uoa.tedi_booking.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService extends GenericService<Chat>{

    @Autowired
    public ChatService(ChatRepository chatRepository){ super(chatRepository); }
}
