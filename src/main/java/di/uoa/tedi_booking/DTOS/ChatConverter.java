package di.uoa.tedi_booking.DTOS;

import di.uoa.tedi_booking.entities.Chat;

import java.util.HashSet;
import java.util.stream.Collectors;

public class ChatConverter {

    public static HashSet<ChatDTO> convertToChatDTOSet(HashSet<Chat> chatSet) {
        return chatSet.stream()
                .map(ChatConverter::convertToChatDTO)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public static ChatDTO convertToChatDTO(Chat chat) {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.chatId = chat.getId();
        chatDTO.firstUserName = chat.getFirstUser().getUsername();
        chatDTO.secondUserName = chat.getSecondUser().getUsername();
        chatDTO.messageList = chat.getMessageList();
        chatDTO.roomId = chat.getConversationForRoom().getId();
        return chatDTO;
    }

    public static ChatDTO convertChatToChatDTO(Chat chat) {
        if (chat == null) {
            return null;
        }

        ChatDTO chatDTO = new ChatDTO();
        chatDTO.chatId = chat.getId();
        chatDTO.firstUserName = chat.getFirstUser().getUsername();
        chatDTO.secondUserName = chat.getSecondUser().getUsername();
        chatDTO.messageList = chat.getMessageList();
        chatDTO.roomId = chat.getConversationForRoom().getId();
        return chatDTO;
    }
}
