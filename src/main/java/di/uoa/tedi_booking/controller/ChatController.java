package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.DTOS.ChatDTO;
import di.uoa.tedi_booking.entities.Chat;
import di.uoa.tedi_booking.entities.Message;
import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController extends GenericController<Chat>{

    private final ChatService chatService;
    @Autowired
    public ChatController(ChatService service){
        super(service);
        this.chatService = service;
    }

    @GetMapping("/getallUsersForChat/{userId}")
    public ResponseEntity<List<User>> getallUsersForChat(@PathVariable int userId) throws IOException {
        try{
            return new ResponseEntity<List<User>>(chatService.getallUsersForChat(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("User not Found", HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/add1")
    public ResponseEntity<ChatDTO> createChat(@RequestBody ChatDTO chat) throws IOException {

        try {
            return new ResponseEntity<ChatDTO>(chatService.addChat(chat), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("ChatModel Already Exist", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/add/message1")
    public ResponseEntity<Message> addMessage2(@RequestBody Message message) throws IOException {
        return new ResponseEntity<Message>(chatService.addMessage2(message), HttpStatus.CREATED);
    }


    @GetMapping("/all/messages/from/chat/{chatId}")
    public ResponseEntity<?> getAllMessagesInChat(@PathVariable int chatId) {
        try {
            List<Message> messageList = this.chatService.getAllMessagesInChat(chatId);
            return ResponseEntity.ok(messageList);
        } catch (Exception e) {
            return new ResponseEntity("Message List not found", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/temp/{id}")
    public ResponseEntity<ChatDTO> getChatById(@PathVariable int id) {
        try {
            return new ResponseEntity<ChatDTO>(chatService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("ChatModel Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/firstUserName/{username}")
    public ResponseEntity<?> getChatByFirstUserName(@PathVariable String username) {
        try {
            HashSet<ChatDTO> byChat = this.chatService.getChatByFirstUserName(username);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("ChatModel Not Exits", HttpStatus.CONFLICT);
        }
    }



    @GetMapping("/secondUserName/{username}")
    public ResponseEntity<?> getChatBySecondUserName(@PathVariable String username) {

        try {
            HashSet<ChatDTO> byChat = this.chatService.getChatBySecondUserName(username);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("ChatModel Not Exits", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getChatByFirstUserNameOrSecondUserName/{username}")
    public ResponseEntity<?> getChatByFirstUserNameOrSecondUserName(@PathVariable String username) {

        try {
            HashSet<ChatDTO> byChat = this.chatService.getChatByFirstUserNameOrSecondUserName(username);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("ChatModel Not Exits", HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/getChatByFirstUserNameAndSecondUserName")
    public ResponseEntity<?> getChatByFirstUserNameAndSecondUserName(@RequestParam("firstUserName") String firstUserName, @RequestParam("secondUserName") String secondUserName){

        try {
            HashSet<ChatDTO> chatByBothEmail = this.chatService.getChatByFirstUserNameAndSecondUserName(firstUserName, secondUserName);
            return new ResponseEntity<>(chatByBothEmail, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("ChatModel Not Exits", HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/message/{chatId}")
    public ResponseEntity<ChatDTO> addMessage(@RequestBody Message add , @PathVariable int chatId) throws Exception {
        return new ResponseEntity<ChatDTO>(chatService.addMessage(add,chatId), HttpStatus.OK);
    }

}
