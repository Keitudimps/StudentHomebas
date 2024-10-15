package za.ac.cput.service.messageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Landlord;
import za.ac.cput.domain.Message;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.User;
import za.ac.cput.repository.MessageRepository;
import za.ac.cput.service.StudentService;
import za.ac.cput.service.landlordService.LandlordService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LandlordService landlordService;

    public Message sendMessage(User sender, User receiver, String content) {

        // Check the type of sender
        if (sender instanceof Student) {
            // Handle logic specific to Student sender
            System.out.println("Sender is a Student");
            System.out.println("Student: " + sender);
            studentService.save((Student) sender);
        } else {
            // Handle logic specific to User sender
            System.out.println("Sender is a Landlord");
            System.out.println("landlord: " + receiver);
            landlordService.save((Landlord) sender);
        }

        // Check the type of receiver
        if (receiver instanceof Student) {
            // Handle logic specific to Student receiver
            System.out.println("Receiver is a Student");
            System.out.println("Student: " + receiver);
            studentService.save((Student) receiver);
        } else {
            // Handle logic specific to User receiver
            System.out.println("Receiver is a Landlord");
            System.out.println("landlord: " + sender);
            landlordService.save((Landlord) receiver);

        }
        Message message = new Message.MessageBuilder()
                .setSender((sender))
                .setReceiver(receiver)
                .setContent(content)
                .setTimestamp(LocalDateTime.now())
                .build();
        System.out.println("To Save: " + message) ;

        return messageRepository.save(message);
    }

    public List<Message> getMessagesForUser(User user) {
        return messageRepository.findBySenderOrReceiver(user, user);
    }
}

