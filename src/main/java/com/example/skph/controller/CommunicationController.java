package com.example.skph.controller;

import com.example.skph.model.communication.Message;
import com.example.skph.model.communication.Notification;
import com.example.skph.service.communication.CommunicationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/communication")
public class CommunicationController {

    private final CommunicationManager communicationManager;

    public CommunicationController(CommunicationManager communicationManager) {
        this.communicationManager = communicationManager;
    }

    @PostMapping("/message")
    public ResponseEntity<Message> sendMessage(
            @RequestParam Long senderId,
            @RequestParam Long recipientId,
            @RequestParam String content,
            @RequestParam String messageType,
            @RequestHeader(value = "Accept-Language", defaultValue = "pl") String language) {

        Locale locale = Locale.forLanguageTag(language);
        Message message = communicationManager.sendMessage(senderId, recipientId, content, messageType, locale);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/notification")
    public ResponseEntity<Notification> sendNotification(
            @RequestParam Long senderId,
            @RequestParam Long recipientId,
            @RequestParam String content,
            @RequestParam String notificationType,
            @RequestHeader(value = "Accept-Language", defaultValue = "pl") String language) {

        Locale locale = Locale.forLanguageTag(language);
        Notification notification = communicationManager.sendNotification(senderId, recipientId, content, notificationType, locale);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/messages/{recipientId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long recipientId) {
        List<Message> messages = communicationManager.getMessagesForRecipient(recipientId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/notifications/{recipientId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long recipientId) {
        List<Notification> notifications = communicationManager.getNotificationsForRecipient(recipientId);
        return ResponseEntity.ok(notifications);
    }
}
