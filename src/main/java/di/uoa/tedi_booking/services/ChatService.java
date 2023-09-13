package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.DTOS.ChatConverter;
import di.uoa.tedi_booking.DTOS.ChatDTO;
import di.uoa.tedi_booking.entities.Chat;
import di.uoa.tedi_booking.entities.Message;
import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.repositories.ChatRepository;
import di.uoa.tedi_booking.repositories.MessageRepository;
import di.uoa.tedi_booking.repositories.RoomRepository;
import di.uoa.tedi_booking.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService extends GenericService<Chat>{

    private final ChatRepository chatRepository;

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    private final RoomRepository roomRepository;


    @Autowired
    public ChatService(ChatRepository repository, MessageRepository messageRepository, UserRepository userRepository,RoomRepository roomRepository){
        super(repository);
        this.chatRepository = repository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }
    @Transactional
    public List<User> getallUsersForChat(int userid) throws Exception {
        List<User> users=userRepository.findAllUsersForChat(userid);
        if (users.isEmpty()){
            throw new Exception();
        }else {
            users.forEach(user -> Hibernate.initialize(user.getRoles()));
            return users;
        }
    }
    @Transactional
    public ChatDTO addChat(ChatDTO chat)  {
        Chat realChat = new Chat();
        realChat.setMessageList(chat.messageList);
        try {
            realChat.setFirstUser(userRepository.getUserByUserName(chat.firstUserName));
            realChat.setSecondUser(userRepository.getUserByUserName(chat.secondUserName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        realChat.setConversationForRoom(roomRepository.findById((long) chat.roomId).orElse(null));
        realChat = chatRepository.save(realChat);

        ChatDTO returnchatDTO = new ChatDTO();
        returnchatDTO.chatId = realChat.getId();
        returnchatDTO.firstUserName = realChat.getFirstUser().getUsername();
        returnchatDTO.secondUserName = realChat.getFirstUser().getUsername();
        returnchatDTO.roomId = realChat.getConversationForRoom().getId();
        if(realChat.getMessageList().size()>0) {
            returnchatDTO.messageList.addAll(realChat.getMessageList());
        }

        return returnchatDTO;
    }
    @Transactional
    public ChatDTO getById(int id) throws Exception {
        Optional<Chat> chatid = chatRepository.findById((long)id);
        if (chatid.isPresent()) {
            return ChatConverter.convertChatToChatDTO(chatid.get());
        } else {
            throw new Exception();
        }
    }


    public HashSet<ChatDTO> getChatByFirstUserName(String username) throws Exception {
        User user1 = userRepository.getUserByUserName(username);
        HashSet<Chat> chat = chatRepository.getChatByFirstUser(user1);

        HashSet<ChatDTO> chat2 = ChatConverter.convertToChatDTOSet(chat);

        if (chat2.isEmpty()) {
            throw new Exception();
        } else {
            return chat2;
        }
    }

    public HashSet<ChatDTO> getChatBySecondUserName(String username) throws Exception {
        User user2 = userRepository.getUserByUserName(username);
        HashSet<Chat> chat = chatRepository.getChatBySecondUser(user2);

        HashSet<ChatDTO> chat2 = ChatConverter.convertToChatDTOSet(chat);
        if (chat2.isEmpty()) {
            throw new Exception();
        } else {
            return chat2;
        }
    }
    public HashSet<ChatDTO> getChatByFirstUserNameOrSecondUserName(String username) throws Exception {
        User user1 = userRepository.getUserByUserName(username);
        HashSet<Chat> chat = chatRepository.getChatByFirstUser(user1);
        HashSet<Chat> chat1 = chatRepository.getChatBySecondUser(user1);

        HashSet<ChatDTO> chat2 = ChatConverter.convertToChatDTOSet(chat);
        HashSet<ChatDTO> chat3 = ChatConverter.convertToChatDTOSet(chat1);

        chat3.addAll(chat2);

        if (chat2.isEmpty() && chat3.isEmpty()) {
            throw new Exception();
        } else if (chat1.isEmpty()) {
            return chat2;
        } else {
            return chat3;
        }
    }
    @Transactional
    public HashSet<ChatDTO> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName) throws Exception {
        User user1 = userRepository.getUserByUserName(firstUserName);
        User user2 = userRepository.getUserByUserName(secondUserName);
        HashSet<Chat> chat = chatRepository.getChatByFirstUserAndSecondUser(user1, user2);
        HashSet<Chat> chat1 = chatRepository.getChatBySecondUserAndFirstUser(user1, user2);

        HashSet<ChatDTO> chat2 = ChatConverter.convertToChatDTOSet(chat);
        HashSet<ChatDTO> chat3 = ChatConverter.convertToChatDTOSet(chat1);


        if (chat2.isEmpty() && chat3.isEmpty()) {
            throw new Exception();
        } else if (chat1.isEmpty()) {
            return chat2;
        } else {
            return chat3;
        }
    }
   @Transactional
    public ChatDTO addMessage(Message add, int chatId) throws Exception {
        Optional<Chat> chat=chatRepository.findById((long)chatId);
        Chat abc=chat.get();

        if(abc.getMessageList()==null){
            List<Message> msg=new ArrayList<>();
            msg.add(add);
            abc.setMessageList(msg);
            return ChatConverter.convertChatToChatDTO(chatRepository.save(abc));
        }else{
            List<Message> rates=abc.getMessageList();
            rates.add(add);
            abc.setMessageList(rates);
            return ChatConverter.convertChatToChatDTO(chatRepository.save(abc));
        }
    }
    @Transactional
    public Message addMessage2(Message message) {
        Message message1 = new Message();

        Optional<Chat> chatid = chatRepository.findById((long)message.getChat().getId());
        if(chatid.isPresent()){
            message1.setChat(chatid.get());
            message1.setReplymessage(message.getReplymessage());
            message1.setSenderEmail(message.getSenderEmail());
        }
        return messageRepository.save(message1);
    }


    public List<Message> getAllMessagesInChat(int chatId) throws Exception {
        Chat chat = chatRepository.getChatById((long)chatId);

        if(chat==null){
            throw new Exception();
        }else {
            return chat.getMessageList();
        }
    }
}
