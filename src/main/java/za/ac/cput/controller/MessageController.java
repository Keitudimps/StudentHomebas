package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Message;
import za.ac.cput.domain.MessageDTO;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;
import za.ac.cput.service.messageService.MessageService;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserRepository userRepository;  // Assuming you have a UserRepository

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDTO messageDTO) {
        System.out.println("Sender: " + messageDTO.getSenderId());
        System.out.println("Receiver: " + messageDTO.getReceiverId());
        User sender = userRepository.findById(messageDTO.getSenderId()).isPresent() ?
                userRepository.findById(messageDTO.getSenderId()).get(): null;

        User receiver =userRepository.findById(messageDTO.getReceiverId()).isPresent() ?
                userRepository.findById(messageDTO.getReceiverId()).get(): null;

        Message message = messageService.sendMessage(sender, receiver, messageDTO.getContent());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/getmessages/{userId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable("userId") Long userId) {
        System.out.println("GetMessages for: " + userId);
        User user = userRepository.findById(userId).orElseThrow();

        List<Message> messages = messageService.getMessagesForUser(user);
        return ResponseEntity.ok(messages);
    }
}
