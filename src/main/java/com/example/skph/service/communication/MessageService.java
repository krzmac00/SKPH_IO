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

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public Message createMessage(Long senderId, Long recipientId, String content, String messageType, Locale locale) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setRecipientId(recipientId);

        // Przechowujemy treść oryginalną, bez tłumaczenia
        message.setContent(content);

        message.setMessageType(Enum.valueOf(MessageType.class, messageType));
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesForRecipient(Long recipientId, Locale locale) {
        List<Message> messages = messageRepository.findByRecipientId(recipientId);

        // Tłumaczenie treści wiadomości na podstawie lokalizacji
        for (Message message : messages) {
            String translatedContent = messageSource.getMessage(message.getContent(), null, locale);
            message.setContent(translatedContent);
        }

        return messages;
    }
}


