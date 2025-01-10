package com.example.skph.service.communication;

import com.example.skph.model.communication.Notification;

import java.util.List;
import java.util.Locale;

public interface INotificationService {
    Notification createNotification(Long senderId, Long recipientId, String content, String notificationType, Locale locale);
    List<Notification> getNotificationsForRecipient(Long recipientId);
}
