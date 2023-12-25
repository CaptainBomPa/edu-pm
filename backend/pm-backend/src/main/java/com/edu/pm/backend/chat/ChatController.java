package com.edu.pm.backend.chat;

import com.edu.pm.backend.model.Message;
import com.edu.pm.backend.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@Controller
@CrossOrigin(origins = {"http://10.0.1.64:3000", "http://localhost:3000"})
@RequiredArgsConstructor
public class ChatController {

    private final MessageRepository messageRepository;

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Message receiveMessage(@Payload Message message) {
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
        return message;
    }
}
