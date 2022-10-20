package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration //включаем конфигурацию
@EnableWebSocketMessageBroker //включаем возможности WebSocket, что позволит обрабатывать сообщения
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override //метод для передачи брокером сообщений клиенту
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic"); //Включение брокера сообщений и настрока одного или нескольких префиксов для фильтрации адресатов,
    }                                                       //нацеленных на брокера, адресатов с префиксом «/topic»

    @Override //метод для добавления конечно точки с перфиксом chat-example
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-example").withSockJS(); //Регистрация STOMP через конечную точку WebSocket по указанному пути сопоставления.
    }

}