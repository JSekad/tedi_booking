package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Chat;
import di.uoa.tedi_booking.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/chat")
public class ChatController extends GenericController<Chat>{

    private final ChatService chatService;
    @Autowired
    public ChatController(ChatService service){
        super(service);
        this.chatService = service;
    }
}
