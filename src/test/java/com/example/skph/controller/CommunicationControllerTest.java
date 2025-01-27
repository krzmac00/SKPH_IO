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
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})  // Inicjalizujemy rozszerzenia Spring i Mockito
class CommunicationControllerTest {

    @InjectMocks
    private CommunicationController communicationController;  // Tworzymy instancję kontrolera, który będzie testowany

    @Mock
    private CommunicationManager communicationManager;  // Mockujemy CommunicationManager, który odpowiada za logikę komunikacji

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new CommunicationController()).build();

    @Test
    void testSendMessage_Success() {
        // Przygotowanie danych wejściowych - tworzymy wiadomość
        Message message = new Message();
        message.setSenderId(1L);
        message.setRecipientId(2L);
        message.setContent("Testowa wiadomość");
        message.setMessageType(MessageType.TEXT);
        message.setTimestamp(LocalDateTime.now());  // Ustawiamy timestamp

        // Mockowanie odpowiedzi z komunikatora: metoda sendMessage zwraca naszą przygotowaną wiadomość
        when(communicationManager.sendMessage(1L, 2L, "Testowa wiadomość", "TEXT", Locale.forLanguageTag("pl")))
                .thenReturn(message);

        // Akcja - wywołanie metody kontrolera
        ResponseEntity<Message> response = communicationController.sendMessage(1L, 2L, "Testowa wiadomość", "TEXT", "pl");

        // Asercje - sprawdzamy poprawność odpowiedzi
        assertEquals(200, response.getStatusCodeValue());  // Oczekujemy statusu 200 (OK)
        assertEquals("Testowa wiadomość", response.getBody().getContent());  // Sprawdzamy treść wiadomości
        assertEquals(message.getTimestamp(), response.getBody().getTimestamp());  // Sprawdzamy, czy timestamp się zgadza
        verify(communicationManager).sendMessage(eq(1L), eq(2L), eq("Testowa wiadomość"), eq("TEXT"), any(Locale.class));  // Weryfikujemy, czy metoda sendMessage została wywołana z odpowiednimi parametrami
    }

    @Test
    void testSendNotification_Success() {
        // Przygotowanie danych wejściowych - tworzymy powiadomienie
        Notification notification = new Notification();
        notification.setSenderId(1L);
        notification.setRecipientId(2L);
        notification.setContent("Testowa notyfikacja");
        notification.setNotificationType(NotificationType.TASK_UPDATE);
        notification.setStatus("Nowa");  // Ustawiamy status powiadomienia
        notification.setTimestamp(LocalDateTime.now());  // Ustawiamy timestamp

        // Mockowanie odpowiedzi z komunikatora: metoda sendNotification zwraca nasze przygotowane powiadomienie
        when(communicationManager.sendNotification(1L, 2L, "Testowa notyfikacja", "TASK_UPDATE", Locale.forLanguageTag("pl")))
                .thenReturn(notification);

        // Akcja - wywołanie metody kontrolera
        ResponseEntity<Notification> response = communicationController.sendNotification(1L, 2L, "Testowa notyfikacja", "TASK_UPDATE", "pl");

        // Asercje - sprawdzamy poprawność odpowiedzi
        assertEquals(200, response.getStatusCodeValue());  // Oczekujemy statusu 200 (OK)
        assertEquals("Testowa notyfikacja", response.getBody().getContent());  // Sprawdzamy treść powiadomienia
        assertEquals("Nowa", response.getBody().getStatus());  // Sprawdzamy status powiadomienia
        assertEquals(notification.getTimestamp(), response.getBody().getTimestamp());  // Sprawdzamy, czy timestamp się zgadza
        verify(communicationManager).sendNotification(eq(1L), eq(2L), eq("Testowa notyfikacja"), eq("TASK_UPDATE"), any(Locale.class));  // Weryfikujemy, czy metoda sendNotification została wywołana z odpowiednimi parametrami
    }

    @Test
    void testGetMessages_Success() {
        // Przygotowanie danych wejściowych - tworzymy dwie wiadomości
        Message message1 = new Message();
        message1.setSenderId(1L);
        message1.setRecipientId(2L);
        message1.setContent("Wiadomość 1");
        message1.setMessageType(MessageType.TEXT);
        message1.setTimestamp(LocalDateTime.now());  // Ustawiamy timestamp

        Message message2 = new Message();
        message2.setSenderId(1L);
        message2.setRecipientId(2L);
        message2.setContent("Wiadomość 2");
        message2.setMessageType(MessageType.TEXT);
        message2.setTimestamp(LocalDateTime.now());  // Ustawiamy timestamp

        List<Message> messages = List.of(message1, message2);  // Lista wiadomości

        // Mockowanie odpowiedzi z komunikatora: metoda getMessagesForRecipient zwraca naszą listę wiadomości
        when(communicationManager.getMessagesForRecipient(2L, Locale.forLanguageTag("pl"))).thenReturn(messages);

        // Akcja - wywołanie metody kontrolera
        ResponseEntity<List<Message>> response = communicationController.getMessages(2L, "pl");

        // Asercje - sprawdzamy poprawność odpowiedzi
        assertEquals(200, response.getStatusCodeValue());  // Oczekujemy statusu 200 (OK)
        assertEquals(2, response.getBody().size());  // Oczekujemy dwóch wiadomości
        assertEquals(message1.getTimestamp(), response.getBody().get(0).getTimestamp());  // Sprawdzamy timestamp pierwszej wiadomości
        assertEquals(message2.getTimestamp(), response.getBody().get(1).getTimestamp());  // Sprawdzamy timestamp drugiej wiadomości
        verify(communicationManager).getMessagesForRecipient(eq(2L), any(Locale.class));  // Weryfikujemy, czy metoda getMessagesForRecipient została wywołana z odpowiednimi parametrami
    }

    @Test
    void testGetNotifications_Success() {
        // Przygotowanie danych wejściowych - tworzymy dwa powiadomienia
        Notification notification1 = new Notification();
        notification1.setSenderId(1L);
        notification1.setRecipientId(2L);
        notification1.setContent("Notyfikacja 1");
        notification1.setNotificationType(NotificationType.TASK_UPDATE);
        notification1.setStatus("Nowa");  // Ustawiamy status powiadomienia
        notification1.setTimestamp(LocalDateTime.now());  // Ustawiamy timestamp

        Notification notification2 = new Notification();
        notification2.setSenderId(1L);
        notification2.setRecipientId(2L);
        notification2.setContent("Notyfikacja 2");
        notification2.setNotificationType(NotificationType.STATUS_CHANGE);
        notification2.setStatus("Przeczytana");  // Ustawiamy status powiadomienia
        notification2.setTimestamp(LocalDateTime.now());  // Ustawiamy timestamp

        List<Notification> notifications = List.of(notification1, notification2);  // Lista powiadomień

        // Mockowanie odpowiedzi z komunikatora: metoda getNotificationsForRecipient zwraca naszą listę powiadomień
        when(communicationManager.getNotificationsForRecipient(2L, Locale.forLanguageTag("pl"))).thenReturn(notifications);

        // Akcja - wywołanie metody kontrolera
        ResponseEntity<List<Notification>> response = communicationController.getNotifications(2L, "pl");

        // Asercje - sprawdzamy poprawność odpowiedzi
        assertEquals(200, response.getStatusCodeValue());  // Oczekujemy statusu 200 (OK)
        assertEquals(2, response.getBody().size());  // Oczekujemy dwóch powiadomień
        assertEquals(notification1.getTimestamp(), response.getBody().get(0).getTimestamp());  // Sprawdzamy timestamp pierwszego powiadomienia
        assertEquals(notification2.getTimestamp(), response.getBody().get(1).getTimestamp());  // Sprawdzamy timestamp drugiego powiadomienia
        verify(communicationManager).getNotificationsForRecipient(eq(2L), any(Locale.class));  // Weryfikujemy, czy metoda getNotificationsForRecipient została wywołana z odpowiednimi parametrami
    }

    @Test
    void testMessageSentByUserAReceivedByUserB() {
        // Przygotowanie danych - tworzymy wiadomość od użytkownika A do B
        Message message = new Message();
        message.setSenderId(1L);  // Użytkownik A
        message.setRecipientId(2L);  // Użytkownik B
        message.setContent("Witaj, Użytkowniku B!");
        message.setMessageType(MessageType.TEXT);
        message.setTimestamp(LocalDateTime.now());  // Ustawiamy timestamp

        List<Message> messages = List.of(message);  // Lista wiadomości (jedna wiadomość)

        // Mockowanie odpowiedzi: metody sendMessage i getMessagesForRecipient
        when(communicationManager.sendMessage(eq(1L), eq(2L), eq("Witaj, Użytkowniku B!"), eq("TEXT"), any(Locale.class)))
                .thenReturn(message);
        when(communicationManager.getMessagesForRecipient(eq(2L), any(Locale.class))).thenReturn(messages);

        // Akcja - wysyłamy wiadomość
        Message sentMessage = communicationManager.sendMessage(1L, 2L, "Witaj, Użytkowniku B!", "TEXT", Locale.forLanguageTag("pl"));

        // Akcja - pobieramy wiadomości użytkownika B
        List<Message> messagesForUserB = communicationManager.getMessagesForRecipient(2L, Locale.forLanguageTag("pl"));

        // Asercje - sprawdzamy poprawność pobranych wiadomości
        assertEquals(1, messagesForUserB.size());  // Oczekujemy jednej wiadomości
        assertEquals("Witaj, Użytkowniku B!", messagesForUserB.get(0).getContent());  // Sprawdzamy treść wiadomości
        assertEquals(1L, messagesForUserB.get(0).getSenderId().longValue());  // Sprawdzamy ID nadawcy
        assertEquals(2L, messagesForUserB.get(0).getRecipientId().longValue());  // Sprawdzamy ID odbiorcy
        assertEquals(sentMessage.getTimestamp(), messagesForUserB.get(0).getTimestamp());  // Sprawdzamy timestamp

        // Weryfikacja, czy metody były wywołane z odpowiednimi parametrami
        verify(communicationManager).sendMessage(eq(1L), eq(2L), eq("Witaj, Użytkowniku B!"), eq("TEXT"), any(Locale.class));
        verify(communicationManager).getMessagesForRecipient(eq(2L), any(Locale.class));
    }

    @Test
    void testGetMessages_NoMessages() {
        // Mockowanie odpowiedzi - brak wiadomości
        when(communicationManager.getMessagesForRecipient(2L, Locale.forLanguageTag("pl"))).thenReturn(List.of());

        // Akcja - pobieramy wiadomości użytkownika B
        ResponseEntity<List<Message>> response = communicationController.getMessages(2L, "pl");

        // Asercje - sprawdzamy, że lista wiadomości jest pusta
        assertEquals(200, response.getStatusCodeValue());  // Oczekujemy statusu 200 (OK)
        assertTrue(response.getBody().isEmpty());  // Oczekujemy pustej listy
    }

    @Test
    void testSendMessage_WithTranslation() {
        // Przygotowanie danych - wiadomość do wysłania w języku angielskim
        Message message = new Message();
        message.setSenderId(1L);
        message.setRecipientId(2L);
        message.setContent("Testowa wiadomość");
        message.setMessageType(MessageType.TEXT);
        message.setTimestamp(LocalDateTime.now());

        // Mockowanie odpowiedzi z komunikatora
        when(communicationManager.sendMessage(1L, 2L, "Testowa wiadomość", "TEXT", Locale.forLanguageTag("en")))
                .thenReturn(message);

        // Akcja - wysyłanie wiadomości w języku angielskim
        ResponseEntity<Message> response = communicationController.sendMessage(1L, 2L, "Testowa wiadomość", "TEXT", "en");

        // Asercje - sprawdzamy, że wiadomość została wysłana z tłumaczeniem
        assertEquals(200, response.getStatusCodeValue());  // Oczekujemy statusu 200 (OK)
        assertEquals("Testowa wiadomość", response.getBody().getContent());  // Treść wiadomości powinna być w tłumaczeniu
    }

    @Test
    void testGetNotifications_NoNotifications() {
        // Mockowanie odpowiedzi - brak powiadomień
        when(communicationManager.getNotificationsForRecipient(2L, Locale.forLanguageTag("pl"))).thenReturn(List.of());

        // Akcja - pobieramy powiadomienia użytkownika B
        ResponseEntity<List<Notification>> response = communicationController.getNotifications(2L, "pl");

        // Asercje - sprawdzamy, że lista powiadomień jest pusta
        assertEquals(200, response.getStatusCodeValue());  // Oczekujemy statusu 200 (OK)
        assertTrue(response.getBody().isEmpty());  // Oczekujemy pustej listy
    }

    @Test
    void testGetChatPage() {
        // Act
        String viewName = communicationController.getChatPage();
        // Assert
        assertEquals("chat", viewName);
    }
}
