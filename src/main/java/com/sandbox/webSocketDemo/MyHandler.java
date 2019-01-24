package com.sandbox.webSocketDemo;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends TextWebSocketHandler {

    private List<WebSocketSession> users;

    //WebSocketConfigで特定のエンドポイントにアクセスしたら初期化されるようになっている
    public MyHandler() {
        users = new ArrayList<>();
    }

    //コネクション確立で発火
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if (users.stream()
                .noneMatch(user -> user.getId().equals(session.getId()))) ;
    }

    //メッセージ受信で発火
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        users.stream().filter(user -> !user.getId().equals(session.getId()))
                .forEach(user -> {
                    try {
                        user.sendMessage(message);
                    } catch (Exception e) {
                        System.out.println(e.fillInStackTrace() + "message can not send to other users");
                    }
                });
    }

    //コネクショクローズで発火
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        users.stream()
                .filter(user -> user.getId().equals(session.getId()))
                .findFirst()
                .ifPresent(user -> users.remove(user));
    }
}
