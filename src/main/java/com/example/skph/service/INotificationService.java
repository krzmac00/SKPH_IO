package com.example.skph.service;

import com.example.skph.model.communication.Notification;

import java.util.List;

public interface INotificationService {
    Notification createNotification(Long senderId, Long recipientId, String content, String notificationType);
    List<Notification> getNotificationsForRecipient(Long recipientId);
}
