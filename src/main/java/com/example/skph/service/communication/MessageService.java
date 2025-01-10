package com.example.skph.service.communication;

import com.example.skph.model.communication.Message;
import com.example.skph.model.communication.MessageType;
import com.example.skph.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;
    private final MessageSource messageSource;

    public MessageService(MessageRepository messageRepository, MessageSource messageSource) {
        this.messageRepository = messageRepository;
        this.messageSource = messageSource;
    }

    public Message createMessage(Long senderId, Long recipientId, String content, String messageType, Locale locale) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setRecipientId(recipientId);

        // Tłumaczenie treści komunikatu
        String translatedContent = messageSource.getMessage(content, null, locale);
        message.setContent(translatedContent);

        message.setMessageType(Enum.valueOf(MessageType.class, messageType));
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public List<Message> getMessagesForRecipient(Long recipientId) {
        return messageRepository.findByRecipientId(recipientId);
    }
}
