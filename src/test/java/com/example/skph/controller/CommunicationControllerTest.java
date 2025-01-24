package com.example.skph.controller;

import com.example.skph.model.communication.Message;
import com.example.skph.model.communication.MessageType;
import com.example.skph.model.communication.Notification;
import com.example.skph.model.communication.NotificationType;
import com.example.skph.repository.MessageRepository;
import com.example.skph.repository.NotificationRepository;
import com.example.skph.service.communication.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Locale;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
class CommunicationControllerTest {

//    @Mock
//    private CommunicationManager communicationManager;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private NotificationRepository notificationRepository;

//    @Mock
//    private MessageService messageService;
//
//    @Mock
//    private NotificationService notificationService;

    @InjectMocks
    private CommunicationController communicationController;

    @Mock
    private IMessageService messageService;

    @Mock
    private INotificationService notificationService;

    @InjectMocks
    private CommunicationManager communicationManager;

    @Test
    void testSendMessage() {
        // Arrange
        Message message = new Message();
        message.setSenderId(1L);
        message.setRecipientId(2L);
        message.setContent("Test message");
        message.setMessageType(MessageType.TEXT);

        when(communicationManager.sendMessage(1L, 2L, "Test message", "TEXT", Locale.forLanguageTag("pl"))).thenReturn(message);

        communicationController.sendMessage(1L, 2L, "Test message", "TEXT", "pl");

        verify(communicationManager).sendMessage(eq(1L), eq(2L), eq("Test message"), eq("TEXT"), any(Locale.class));
    }
    @Test
    void testSendMessage_Success() {
        // Arrange
        Message message = new Message();
        message.setSenderId(1L);
        message.setRecipientId(2L);
        message.setContent("Test message");
        message.setMessageType(MessageType.TEXT);

        when(communicationManager.sendMessage(1L, 2L, "Test message", "TEXT", Locale.forLanguageTag("pl")))
                .thenReturn(message);

        // Act
        ResponseEntity<Message> response = communicationController.sendMessage(1L, 2L, "Test message", "TEXT", "pl");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test message", response.getBody().getContent());
        verify(communicationManager).sendMessage(eq(1L), eq(2L), eq("Test message"), eq("TEXT"), any(Locale.class));
    }

    @Test
    void testSendNotification_Success() {
        // Arrange
        Notification notification = new Notification();
        notification.setSenderId(1L);
        notification.setRecipientId(2L);
        notification.setContent("Test notification");
        notification.setNotificationType(NotificationType.TASK_UPDATE);

        when(communicationManager.sendNotification(1L, 2L, "Test notification", "TASK_UPDATE", Locale.forLanguageTag("pl")))
                .thenReturn(notification);

        // Act
        ResponseEntity<Notification> response = communicationController.sendNotification(1L, 2L, "Test notification", "TASK_UPDATE", "pl");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test notification", response.getBody().getContent());
        verify(communicationManager).sendNotification(eq(1L), eq(2L), eq("Test notification"), eq("TASK_UPDATE"), any(Locale.class));
    }
    @Test
    void testGetMessages_Success() {
        // Arrange
        Message message1 = new Message();
        message1.setSenderId(1L);
        message1.setRecipientId(2L);
        message1.setContent("Message 1");
        message1.setMessageType(MessageType.TEXT);

        Message message2 = new Message();
        message2.setSenderId(1L);
        message2.setRecipientId(2L);
        message2.setContent("Message 2");
        message2.setMessageType(MessageType.TEXT);

        List<Message> messages = List.of(message1, message2);
        when(communicationManager.getMessagesForRecipient(2L, Locale.forLanguageTag("pl"))).thenReturn(messages);

        // Act
        ResponseEntity<List<Message>> response = communicationController.getMessages(2L, "pl");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(communicationManager).getMessagesForRecipient(eq(2L), any(Locale.class));
    }
    @Test
    void testGetNotifications_Success() {
        // Arrange
        Notification notification1 = new Notification();
        notification1.setSenderId(1L);
        notification1.setRecipientId(2L);
        notification1.setContent("Notification 1");
        notification1.setNotificationType(NotificationType.TASK_UPDATE);

        Notification notification2 = new Notification();
        notification2.setSenderId(1L);
        notification2.setRecipientId(2L);
        notification2.setContent("Notification 2");
        notification2.setNotificationType(NotificationType.STATUS_CHANGE);

        List<Notification> notifications = List.of(notification1, notification2);
        when(communicationManager.getNotificationsForRecipient(2L, Locale.forLanguageTag("pl"))).thenReturn(notifications);

        // Act
        ResponseEntity<List<Notification>> response = communicationController.getNotifications(2L, "pl");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(communicationManager).getNotificationsForRecipient(eq(2L), any(Locale.class));
    }
    @Test
    void testGetChatPage() {
        // Act
        String viewName = communicationController.getChatPage();

        // Assert
        assertEquals("chat", viewName);
    }
    @Test
    void testCreateMessage_Success() {
        // Arrange
        Message message = new Message();
        message.setSenderId(1L);
        message.setRecipientId(2L);
        message.setContent("Test message");
        message.setMessageType(MessageType.TEXT);

        when(messageRepository.save(any(Message.class))).thenReturn(message);

        // Act
        Message createdMessage = messageService.createMessage(1L, 2L, "Test message", "TEXT", Locale.forLanguageTag("pl"));

        // Assert
        assertNotNull(createdMessage);
        assertEquals("Test message", createdMessage.getContent());
        verify(messageRepository).save(any(Message.class));
    }
//    @Test
//    void testGetMessagesForRecipient_Success() {
//        // Arrange
//        Message message = new Message();
//        message.setSenderId(1L);
//        message.setRecipientId(2L);
//        message.setContent("Test message");
//        message.setMessageType(MessageType.TEXT);
//        List<Message> messages = List.of(message);
//
//        when(messageRepository.findByRecipientId(2L)).thenReturn(messages);
//        when(messageSource.getMessage(eq("Test message"), any(), eq(Locale.forLanguageTag("pl")))).thenReturn("Przetłumaczone wiadomość");
//
//        // Act
//        List<Message> resultMessages = messageService.getMessagesForRecipient(2L, Locale.forLanguageTag("pl"));
//
//        // Assert
//        assertEquals(1, resultMessages.size());
//        assertEquals("Przetłumaczone wiadomość", resultMessages.get(0).getContent());
//        verify(messageRepository).findByRecipientId(eq(2L));
//    }
    @Test
    void testCreateNotification_Success() {
        // Arrange
        Notification notification = new Notification();
        notification.setSenderId(1L);
        notification.setRecipientId(2L);
        notification.setContent("Test notification");
        notification.setNotificationType(NotificationType.TASK_UPDATE);

        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        // Act
        Notification createdNotification = notificationService.createNotification(1L, 2L, "Test notification", "TASK_UPDATE", Locale.forLanguageTag("pl"));

        // Assert
        assertNotNull(createdNotification);
        assertEquals("Test notification", createdNotification.getContent());
        verify(notificationRepository).save(any(Notification.class));
    }
    @Test
    void testMessageSentByUserAReceivedByUserB() {
        // Arrange
        Message message = new Message();
        message.setSenderId(1L); // Użytkownik A
        message.setRecipientId(2L); // Użytkownik B
        message.setContent("Hello, User B!");
        message.setMessageType(MessageType.TEXT);

        // Mockowanie metod messageService
        when(messageService.createMessage(eq(1L), eq(2L), eq("Hello, User B!"), eq("TEXT"), any(Locale.class)))
                .thenReturn(message);

        when(messageService.getMessagesForRecipient(eq(2L), any(Locale.class)))
                .thenReturn(List.of(message));

        // Act
        // Wyślij wiadomość
        Message sentMessage = communicationManager.sendMessage(1L, 2L, "Hello, User B!", "TEXT", Locale.forLanguageTag("pl"));

        // Pobierz wiadomości dla użytkownika B
        List<Message> messagesForUserB = communicationManager.getMessagesForRecipient(2L, Locale.forLanguageTag("pl"));

        // Assert
        assertNotNull(messagesForUserB);
        assertEquals(1, messagesForUserB.size());
        assertEquals("Hello, User B!", messagesForUserB.get(0).getContent());
        assertEquals(1L, messagesForUserB.get(0).getSenderId().longValue());
        assertEquals(2L, messagesForUserB.get(0).getRecipientId().longValue());

        // Weryfikacja interakcji z mockami
        verify(messageService).createMessage(eq(1L), eq(2L), eq("Hello, User B!"), eq("TEXT"), any(Locale.class));
        verify(messageService).getMessagesForRecipient(eq(2L), any(Locale.class));
    }

}
