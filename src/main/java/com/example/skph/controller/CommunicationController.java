package com.example.skph.controller;

import com.example.skph.model.communication.Message;
import com.example.skph.model.communication.Notification;
import com.example.skph.service.communication.CommunicationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller // Zmieniono na @Controller, ponieważ obsługujemy również widoki HTML
@RequestMapping("/communication")
public class CommunicationController {

    @Autowired
    private CommunicationManager communicationManager;

    @PostMapping("/message")
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable Long recipientId,
            @RequestHeader(value = "Accept-Language", defaultValue = "pl") String language) {

        Locale locale = Locale.forLanguageTag(language);
        List<Message> messages = communicationManager.getMessagesForRecipient(recipientId, locale);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/notifications/{recipientId}")
    @ResponseBody
    public ResponseEntity<List<Notification>> getNotifications(
            @PathVariable Long recipientId,
            @RequestHeader(value = "Accept-Language", defaultValue = "pl") String language) {

        Locale locale = Locale.forLanguageTag(language);
        List<Notification> notifications = communicationManager.getNotificationsForRecipient(recipientId, locale);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/translations")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getTranslations(@RequestParam("lang") String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        Map<String, String> translations = new HashMap<>();
        bundle.keySet().forEach(key -> translations.put(key, bundle.getString(key)));

        return ResponseEntity.ok(translations);
    }

    // Nowe mapowanie do pliku chat.html
    @GetMapping("/chat")
    public String getChatPage() {
        return "chat"; // Zwraca widok HTML o nazwie "chat.html"
    }
}
