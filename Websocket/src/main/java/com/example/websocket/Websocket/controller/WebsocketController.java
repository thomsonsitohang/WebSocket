package com.example.websocket.Websocket.controller;

import com.example.websocket.Websocket.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsocketController {
    @GetMapping("/websocket")
    public String index() {
        return "websocket.html";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(Message message) throws Exception {
        return new Message(message.getDetail(), message.getDateTime());
    }
}
