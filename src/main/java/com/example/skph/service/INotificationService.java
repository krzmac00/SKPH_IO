package com.example.skph.service;

import com.example.skph.model.Notification;

import java.util.List;

public interface INotificationService {
    Notification createNotification(Long senderId, Long recipientId, String content, String notificationType);
    List<Notification> getNotifications(Long recipientId);
}
