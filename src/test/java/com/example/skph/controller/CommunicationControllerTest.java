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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
class CommunicationControllerTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private CommunicationController communicationController;

    @Mock
    private IMessageService messageService;

    @Mock
    private INotificationService notificationService;

    @Mock
    private CommunicationManager communicationManager;

    @Test
    void testSendMessage_Success() {
        // Przygotowanie danych
        Message message = new Message();
        message.setSenderId(1L);
        message.setRecipientId(2L);
        message.setContent("Testowa wiadomość");
        message.setMessageType(MessageType.TEXT);
        message.setTimestamp(LocalDateTime.now());  // Ustawienie timestampu

        when(communicationManager.sendMessage(1L, 2L, "Testowa wiadomość", "TEXT", Locale.forLanguageTag("pl")))
                .thenReturn(message);  // Mockowanie metody sendMessage

        // Akcja
        ResponseEntity<Message> response = communicationController.sendMessage(1L, 2L, "Testowa wiadomość", "TEXT", "pl");

        // Asercje
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Testowa wiadomość", response.getBody().getContent());
        assertEquals(message.getTimestamp(), response.getBody().getTimestamp());  // Asercja dla timestampu
        verify(communicationManager).sendMessage(eq(1L), eq(2L), eq("Testowa wiadomość"), eq("TEXT"), any(Locale.class));
    }

    @Test
    void testSendNotification_Success() {
        // Przygotowanie danych
        Notification notification = new Notification();
        notification.setSenderId(1L);
        notification.setRecipientId(2L);
        notification.setContent("Testowa notyfikacja");
        notification.setNotificationType(NotificationType.TASK_UPDATE);
        notification.setStatus("Nowa");  // Ustawienie statusu
        notification.setTimestamp(LocalDateTime.now());  // Ustawienie timestampu

        when(communicationManager.sendNotification(1L, 2L, "Testowa notyfikacja", "TASK_UPDATE", Locale.forLanguageTag("pl")))
                .thenReturn(notification);

        // Akcja
        ResponseEntity<Notification> response = communicationController.sendNotification(1L, 2L, "Testowa notyfikacja", "TASK_UPDATE", "pl");

        // Asercje
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Testowa notyfikacja", response.getBody().getContent());
        assertEquals("Nowa", response.getBody().getStatus());  // Sprawdzamy status
        assertEquals(notification.getTimestamp(), response.getBody().getTimestamp());  // Asercja dla timestampu
        verify(communicationManager).sendNotification(eq(1L), eq(2L), eq("Testowa notyfikacja"), eq("TASK_UPDATE"), any(Locale.class));
    }

    @Test
    void testGetMessages_Success() {
        // Przygotowanie danych
        Message message1 = new Message();
        message1.setSenderId(1L);
        message1.setRecipientId(2L);
        message1.setContent("Wiadomość 1");
        message1.setMessageType(MessageType.TEXT);
        message1.setTimestamp(LocalDateTime.now());  // Ustawienie timestampu

        Message message2 = new Message();
        message2.setSenderId(1L);
        message2.setRecipientId(2L);
        message2.setContent("Wiadomość 2");
        message2.setMessageType(MessageType.TEXT);
        message2.setTimestamp(LocalDateTime.now());  // Ustawienie timestampu

        List<Message> messages = List.of(message1, message2);
        when(communicationManager.getMessagesForRecipient(2L, Locale.forLanguageTag("pl"))).thenReturn(messages);

        // Akcja
        ResponseEntity<List<Message>> response = communicationController.getMessages(2L, "pl");

        // Asercje
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals(message1.getTimestamp(), response.getBody().get(0).getTimestamp());  // Asercja dla timestampu
        assertEquals(message2.getTimestamp(), response.getBody().get(1).getTimestamp());  // Asercja dla timestampu
        verify(communicationManager).getMessagesForRecipient(eq(2L), any(Locale.class));
    }

    @Test
    void testGetNotifications_Success() {
        // Przygotowanie danych
        Notification notification1 = new Notification();
        notification1.setSenderId(1L);
        notification1.setRecipientId(2L);
        notification1.setContent("Notyfikacja 1");
        notification1.setNotificationType(NotificationType.TASK_UPDATE);
        notification1.setStatus("Nowa");  // Ustawienie statusu
        notification1.setTimestamp(LocalDateTime.now());  // Ustawienie timestampu

        Notification notification2 = new Notification();
        notification2.setSenderId(1L);
        notification2.setRecipientId(2L);
        notification2.setContent("Notyfikacja 2");
        notification2.setNotificationType(NotificationType.STATUS_CHANGE);
        notification2.setStatus("Przeczytana");  // Ustawienie statusu
        notification2.setTimestamp(LocalDateTime.now());  // Ustawienie timestampu

        List<Notification> notifications = List.of(notification1, notification2);
        when(communicationManager.getNotificationsForRecipient(2L, Locale.forLanguageTag("pl"))).thenReturn(notifications);

        // Akcja
        ResponseEntity<List<Notification>> response = communicationController.getNotifications(2L, "pl");

        // Asercje
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals(notification1.getTimestamp(), response.getBody().get(0).getTimestamp());  // Asercja dla timestampu
        assertEquals(notification2.getTimestamp(), response.getBody().get(1).getTimestamp());  // Asercja dla timestampu
        verify(communicationManager).getNotificationsForRecipient(eq(2L), any(Locale.class));
    }

    @Test
    void testMessageSentByUserAReceivedByUserB() {
        // Przygotowanie danych
        Message message = new Message();
        message.setSenderId(1L); // Użytkownik A
        message.setRecipientId(2L); // Użytkownik B
        message.setContent("Witaj, Użytkowniku B!");
        message.setMessageType(MessageType.TEXT);
        message.setTimestamp(LocalDateTime.now());  // Ustawienie timestampu

        // Mockowanie metod messageService
        when(messageService.createMessage(eq(1L), eq(2L), eq("Witaj, Użytkowniku B!"), eq("TEXT"), any(Locale.class)))
                .thenReturn(message);

        when(messageService.getMessagesForRecipient(eq(2L), any(Locale.class)))
                .thenReturn(List.of(message));

        // Akcja
        // Wyślij wiadomość
        Message sentMessage = communicationManager.sendMessage(1L, 2L, "Witaj, Użytkowniku B!", "TEXT", Locale.forLanguageTag("pl"));

        // Pobierz wiadomości dla użytkownika B
        List<Message> messagesForUserB = communicationManager.getMessagesForRecipient(2L, Locale.forLanguageTag("pl"));

        // Asercje
        assertEquals(1, messagesForUserB.size());
        assertEquals("Witaj, Użytkowniku B!", messagesForUserB.get(0).getContent());
        assertEquals(1L, messagesForUserB.get(0).getSenderId().longValue());
        assertEquals(2L, messagesForUserB.get(0).getRecipientId().longValue());
        assertEquals(sentMessage.getTimestamp(), messagesForUserB.get(0).getTimestamp());  // Asercja dla timestampu

        // Weryfikacja interakcji z mockami
        verify(messageService).createMessage(eq(1L), eq(2L), eq("Witaj, Użytkowniku B!"), eq("TEXT"), any(Locale.class));
        verify(messageService).getMessagesForRecipient(eq(2L), any(Locale.class));
    }
    @Test
    void testGetMessages_NoMessages() {
        // Przygotowanie danych
        when(communicationManager.getMessagesForRecipient(2L, Locale.forLanguageTag("pl"))).thenReturn(List.of());

        // Akcja
        ResponseEntity<List<Message>> response = communicationController.getMessages(2L, "pl");

        // Asercje
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());  // Sprawdzamy, czy lista jest pusta
    }
    @Test
    void testSendMessage_WithTranslation() {
        // Przygotowanie danych
        Message message = new Message();
        message.setSenderId(1L);
        message.setRecipientId(2L);
        message.setContent("Testowa wiadomość");
        message.setMessageType(MessageType.TEXT);
        message.setTimestamp(LocalDateTime.now());

        when(communicationManager.sendMessage(1L, 2L, "Testowa wiadomość", "TEXT", Locale.forLanguageTag("en")))
                .thenReturn(message);

        // Akcja
        ResponseEntity<Message> response = communicationController.sendMessage(1L, 2L, "Testowa wiadomość", "TEXT", "en");

        // Asercje
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Testowa wiadomość", response.getBody().getContent());  // Treść wiadomości powinna być w tłumaczeniu
    }

    @Test
    void testGetNotifications_NoNotifications() {
        when(communicationManager.getNotificationsForRecipient(2L, Locale.forLanguageTag("pl"))).thenReturn(List.of());

        ResponseEntity<List<Notification>> response = communicationController.getNotifications(2L, "pl");

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }

}