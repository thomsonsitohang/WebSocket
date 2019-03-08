package com.example.websocket.Websocket.service;

import com.example.websocket.Websocket.entity.Message;
import com.example.websocket.Websocket.repository.MessageRepo;
import com.example.websocket.Websocket.util.Constant;
import com.example.websocket.Websocket.util.SimpleStompSessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WebSocketService {
    @Autowired
    MessageRepo messageRepo;
    public void saveMessage(Message message){
        messageRepo.save(message);
    }
    public Iterable<Message> listMessage(){
        return messageRepo.findAll();
    }

    public void sendMessage(Message message) throws Exception{
        WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
        List<Transport> transports = new ArrayList<Transport>(1);
        transports.add(new WebSocketTransport(simpleWebSocketClient));
        SockJsClient sockJsClient = new SockJsClient(transports);

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        SimpleStompSessionHandler sessionHandler = new SimpleStompSessionHandler();

        StompSession session = stompClient.connect(Constant.WEB_SOCKET_URL, sessionHandler).get();
        session.send(Constant.WEB_SOCKET_TOPIC, message);
    }
}
