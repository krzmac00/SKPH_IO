package com.example.skph.service;

import com.example.skph.model.communication.Notification;
import com.example.skph.model.communication.NotificationType;
import com.example.skph.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService implements INotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Long senderId, Long recipientId, String content, String notificationType) {
        Notification notification = new Notification();

        notification.setSenderId(senderId);
        notification.setRecipientId(recipientId);
        notification.setContent(content);
        notification.setNotificationType(Enum.valueOf(NotificationType.class, notificationType));
        notification.setTimestamp(LocalDateTime.now());
        notification.setStatus("UNREAD");

        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForRecipient(Long recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }
}
