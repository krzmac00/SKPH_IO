package com.example.skph.controller;

import com.example.skph.model.Message;
import com.example.skph.model.Notification;
import com.example.skph.service.CommunicationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class CommunicationController {

    private final CommunicationManager communicationManager;

    @Autowired
    public CommunicationController(CommunicationManager communicationManager) {
        this.communicationManager = communicationManager;
    }

    // Endpoint do wysyłania wiadomości
    @PostMapping("/message")
    public ResponseEntity<Message> sendMessage(
            @RequestParam Long senderId,
            @RequestParam Long recipientId,
            @RequestParam String content,
            @RequestParam String messageType) {
        Message message = communicationManager.sendMessage(senderId, recipientId, content, messageType);
        return ResponseEntity.ok(message);
    }

    // Endpoint do wysyłania powiadomień
    @PostMapping("/notification")
    public ResponseEntity<Notification> sendNotification(
            @RequestParam Long senderId,
            @RequestParam Long recipientId,
            @RequestParam String content,
            @RequestParam String notificationType) {
        Notification notification = communicationManager.sendNotification(senderId, recipientId, content, notificationType);
        return ResponseEntity.ok(notification);
    }

    // Endpoint do pobierania wiadomości użytkownika (id użytkownika z sesji)
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages(@RequestHeader Long userId) {
        List<Message> messages = communicationManager.getMessagesForRecipient(userId); // id pobierane z nagłówka sesji
        return ResponseEntity.ok(messages);
    }

    // Endpoint do pobierania powiadomień użytkownika (id użytkownika z sesji)
    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotifications(@RequestHeader Long userId) {
        List<Notification> notifications = communicationManager.getNotificationsForRecipient(userId);
        return ResponseEntity.ok(notifications);
    }
}
