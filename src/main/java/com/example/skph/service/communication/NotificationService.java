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

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public Notification createNotification(Long senderId, Long recipientId, String content, String notificationType, Locale locale) {
        Notification notification = new Notification();

        notification.setSenderId(senderId);
        notification.setRecipientId(recipientId);

        // Przechowujemy treść oryginalną, bez tłumaczenia
        notification.setContent(content);

        notification.setNotificationType(Enum.valueOf(NotificationType.class, notificationType));
        notification.setTimestamp(LocalDateTime.now());
        notification.setStatus("UNREAD");

        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsForRecipient(Long recipientId, Locale locale) {
        List<Notification> notifications = notificationRepository.findByRecipientId(recipientId);

        // Tłumaczenie treści powiadomienia na podstawie lokalizacji
        for (Notification notification : notifications) {
            String translatedContent = messageSource.getMessage(notification.getContent(), null, locale);
            notification.setContent(translatedContent);
        }

        return notifications;
    }
}

