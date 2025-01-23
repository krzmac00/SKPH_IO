package com.example.skph.service.communication;

import com.example.skph.model.communication.Message;
import com.example.skph.model.communication.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CommunicationManager {

    @Autowired
    private IMessageService messageService;

    @Autowired
    private INotificationService notificationService;

    public Message sendMessage(Long senderId, Long recipientId, String content, String messageType, Locale locale) {
        return messageService.createMessage(senderId, recipientId, content, messageType, locale);
    }

    public Notification sendNotification(Long senderId, Long recipientId, String content, String notificationType, Locale locale) {
        return notificationService.createNotification(senderId, recipientId, content, notificationType, locale);
    }

    public List<Message> getMessagesForRecipient(Long recipientId, Locale locale) {
        return messageService.getMessagesForRecipient(recipientId, locale);
    }

    public List<Notification> getNotificationsForRecipient(Long recipientId, Locale locale) {
        return notificationService.getNotificationsForRecipient(recipientId, locale);
    }
}

