package com.example.skph.service.communication;

import com.example.skph.model.communication.Messagable;
import com.example.skph.model.communication.Message;
import com.example.skph.model.communication.MessageType;
import com.example.skph.model.communication.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;


@Service
public class CommunicationManager {

    private final MessageService messageService;
    private final NotificationService notificationService;

    public CommunicationManager(MessageService messageService, NotificationService notificationService) {
        this.messageService = messageService;
        this.notificationService = notificationService;
    }

    public Message sendMessage(Long senderId, Long recipientId, String content, String messageType, Locale locale) {
        return messageService.createMessage(senderId, recipientId, content, messageType, locale);
    }

    public Notification sendNotification(Long senderId, Long recipientId, String content, String notificationType, Locale locale) {
        return notificationService.createNotification(senderId, recipientId, content, notificationType, locale);
    }

    public List<Message> getMessagesForRecipient(Long recipientId) {
        return messageService.getMessagesForRecipient(recipientId);
    }

    public List<Notification> getNotificationsForRecipient(Long recipientId) {
        return notificationService.getNotificationsForRecipient(recipientId);
    }
}
