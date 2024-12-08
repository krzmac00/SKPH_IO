package com.example.skph.service;

import com.example.skph.model.Messagable;
import com.example.skph.model.Message;
import com.example.skph.model.MessageType;
import com.example.skph.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunicationManager {

    private final MessageService messageService;
    private final NotificationService notificationService;

    @Autowired
    public CommunicationManager(MessageService messageService, NotificationService notificationService) {
        this.messageService = messageService;
        this.notificationService = notificationService;
    }

    public Message sendMessage(Long senderId, Long recipientId, String content, String messageType) {
        return messageService.createMessage(senderId, recipientId, content, messageType);
    }

    public Notification sendNotification(Long senderId, Long recipientId, String content, String notificationType) {
        return notificationService.createNotification(senderId, recipientId, content, notificationType);
    }

    public String createMessage(Messagable messagable, MessageType messageType) {
        return "Typ komunikatu: " + messageType + "\nTreść: " + messagable.getMessageContent();
    }


    public List<Message> getMessagesForRecipient(Long recipientId) {
        return messageService.getMessagesForRecipient(recipientId);
    }

    public List<Notification> getNotificationsForRecipient(Long recipientId) {
        return notificationService.getNotificationsForRecipient(recipientId);
    }
}
