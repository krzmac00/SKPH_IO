package com.example.skph.service.communication;

import com.example.skph.model.communication.Message;

import java.util.List;
import java.util.Locale;

public interface IMessageService {
    Message createMessage(Long senderId, Long recipientId, String content, String messageType, Locale locale);
    List<Message> getMessagesForRecipient(Long recipientId);
}
