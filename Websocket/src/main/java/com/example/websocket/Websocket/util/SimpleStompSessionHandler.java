package com.example.websocket.Websocket.util;

import com.example.websocket.Websocket.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;

public class SimpleStompSessionHandler implements StompSessionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleStompSessionHandler.class);
    @Override
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        LOGGER.info("Connected: " + stompSession.getSessionId());
    }

    @Override
    public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
        LOGGER.info("Exception: " + stompSession.getSessionId());
    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable throwable) {
        LOGGER.info("Transport Error: "+ stompSession.getSessionId());
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println(payload.toString());

    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        // TODO Auto-generated method stub
        return Message.class;
    }
}
