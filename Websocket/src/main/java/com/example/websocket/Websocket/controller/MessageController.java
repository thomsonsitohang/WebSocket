package com.example.websocket.Websocket.controller;

import com.example.websocket.Websocket.entity.Message;
import com.example.websocket.Websocket.service.WebSocketService;
import com.example.websocket.Websocket.util.Constant;
import com.example.websocket.Websocket.util.SimpleStompSessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import java.util.*;

@RestController
public class MessageController {
    @Autowired
    WebSocketService webSocketService;

    @PostMapping("message")
    public Map getMessage(@RequestParam String param){
        Map response = new HashMap();
        try {
            Message message = new Message(param, new Date());
            webSocketService.saveMessage(message);
            webSocketService.sendMessage(message);
            response.put("code", 200);
            response.put("message", "success");

        }catch (Exception ex){
            response.put("code", 500);
            response.put("message", "failed");
        }
        return response;
    }

    @GetMapping("message")
    public Iterable<Message> listMessage(){
        return webSocketService.listMessage();
    }


}
