package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/webs") //обращение к клиентской части по адресу webs
    public String getChat(){
        return "index";
    }

    @MessageMapping("/chat.send") //связывание метода контроллера с настроенной конечной точкой
    @SendTo("/topic") //Все подписчики на пункте назначения « / topic » получат сообщение
    public String sendMessage(@Payload final String chatMessage){
        return chatMessage;
    }
}
