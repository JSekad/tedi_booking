package di.uoa.tedi_booking.DTOS;

import com.fasterxml.jackson.annotation.JsonIgnore;
import di.uoa.tedi_booking.entities.Message;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.Serializable;
import java.util.List;

public class ChatDTO implements Serializable {

    public int chatId;
    public String firstUserName;
    public String secondUserName;
    public List<Message> messageList;

    public Integer roomId;
}
