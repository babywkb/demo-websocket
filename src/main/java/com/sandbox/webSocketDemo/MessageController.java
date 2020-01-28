package com.sandbox.webSocketDemo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    ChatHistoryDao chatHistoryDao;

    public MessageController(ChatHistoryDao chatHistoryDao) {
        this.chatHistoryDao = chatHistoryDao;
    }

    @MessageMapping("/all")
    @SendTo("/topic/all")
    public Map<String, String> post(@Payload Map<String, String> message) {
        message.put("timestamp", Long.toString(System.currentTimeMillis()));
        return message;
    }

//    @RequestMapping("/history")
//    public Map<String, String> getChatHistory() {
//        Map<String, String> history = new HashMap<>();
//        history.put("history", "Under Construction");
//        return history;
//    }

    @RequestMapping("/history")
    public List<Map<String, String>> getChatHistory() {
        return chatHistoryDao.get();
    }
}
