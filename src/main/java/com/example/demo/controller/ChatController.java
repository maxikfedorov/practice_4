package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/webs")
    public String getChat(){
        return "indexxx";
    }


    @MessageMapping("/chat.send")
    @SendTo("/topic")
    public String sendMessage(@Payload final String chatMessage){
        return chatMessage;
    }
}
