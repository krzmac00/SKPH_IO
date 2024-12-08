package com.example.skph.controller;

import com.example.skph.model.Message;
import com.example.skph.model.Notification;
import com.example.skph.service.CommunicationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communication")
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

    // Endpoint do pobierania wiadomości użytkownika
    @GetMapping("/messages/{recipientId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long recipientId) {
        List<Message> messages = communicationManager.getMessagesForRecipient(recipientId); // Komunikacja z managerem
        return ResponseEntity.ok(messages);
    }


    @GetMapping("/notifications/{recipientId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long recipientId) {
        List<Notification> notifications = communicationManager.getNotificationsForRecipient(recipientId); // Komunikacja z managerem
        return ResponseEntity.ok(notifications);
    }

}
