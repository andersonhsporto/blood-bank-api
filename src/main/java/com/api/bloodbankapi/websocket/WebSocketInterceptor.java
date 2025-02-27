package com.api.bloodbankapi.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

@Slf4j
@Configuration
public class WebSocketInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null) {
            StompCommand command = accessor.getCommand();
            if (command != null) {
                switch (command) {
                    case CONNECT:
                        log.info("New WebSocket connection established.");
                        break;
                    case DISCONNECT:
                        log.info("WebSocket connection closed.");
                        break;
                    case SUBSCRIBE:
                        log.info("Client subscribed to: {}", accessor.getDestination());
                        break;
                    case UNSUBSCRIBE:
                        log.info("Client unsubscribed from: {}", accessor.getDestination());
                        break;
                    case SEND:
                        log.info("Message sent to: {}", accessor.getDestination());
                        break;
                    default:
                        break;
                }
            }
        }
        return message;
    }
}
