package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Chat;
import di.uoa.tedi_booking.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/chat")
public class ChatController extends GenericController<Chat>{

    @Autowired
    public ChatController(ChatRepository chatRepository){super(chatRepository);}
}
