package za.ac.cput.service.messageService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.MessageDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MessageServiceTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String BASE_URL = "http://localhost:8080/StudentHomeBas/messages";

    @Autowired

    private MessageService messageService;
    private long landlordSenderID;
    private long landlordRecieverID;
    private long studentSenderID;
    private static  String content;

    @BeforeEach
    @Order(1)
    void setUp() {
        landlordRecieverID = 4;
        studentSenderID = 5;
        content = "Heyy!";
    }

    @Test
    @Order(2)
    void sendMessage() {
        String url = BASE_URL + "/send";
        MessageDTO message = new MessageDTO(studentSenderID , landlordRecieverID , content);
        ResponseEntity<MessageDTO> postResponse = testRestTemplate.postForEntity(url, message, MessageDTO.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        System.out.println("Message Sent: " + postResponse.getBody());

    }

    @Test
    void getMessagesForUser() {
        String url = BASE_URL + "/getmessages/" + landlordRecieverID;
        System.out.println(url);

        ResponseEntity<List> response = testRestTemplate.getForEntity(url,  List.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Messages: " + response.getBody());
    }
}