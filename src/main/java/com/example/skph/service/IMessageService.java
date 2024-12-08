package com.example.skph.service;

import com.example.skph.model.Message;

import java.util.List;

public interface IMessageService {
    Message createMessage(Long senderId, Long recipientId, String content, String messageType);
    List<Message> getMessageHistory(Long recipientId);
}
