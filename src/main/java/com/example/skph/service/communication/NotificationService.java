package com.example.skph.service.communication;

import com.example.skph.model.communication.Notification;
import com.example.skph.model.communication.NotificationType;
import com.example.skph.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class NotificationService implements INotificationService {

    private final NotificationRepository notificationRepository;
    private final MessageSource messageSource;

    public NotificationService(NotificationRepository notificationRepository, MessageSource messageSource) {
        this.notificationRepository = notificationRepository;
        this.messageSource = messageSource;
    }

    public Notification createNotification(Long senderId, Long recipientId, String content, String notificationType, Locale locale) {
        Notification notification = new Notification();

        notification.setSenderId(senderId);
        notification.setRecipientId(recipientId);

        // Tłumaczenie treści powiadomienia
        String translatedContent = messageSource.getMessage(content, null, locale);
        notification.setContent(translatedContent);

        notification.setNotificationType(Enum.valueOf(NotificationType.class, notificationType));
        notification.setTimestamp(LocalDateTime.now());
        notification.setStatus("UNREAD");

        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForRecipient(Long recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }
}