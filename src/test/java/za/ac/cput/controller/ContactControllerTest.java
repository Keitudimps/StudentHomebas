package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8080/StudentHomeBas/contact";
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;

    @BeforeEach
    void setUp() {
        Address address1 = AddressFactory.buildAddress("9 Lower Street", "Mowbray", "Cape Town", "5100");
        Address address2 = AddressFactory.buildAddress("15 Upper Road", "Gardens", "Cape Town", "8001");
        Address address3 = AddressFactory.buildAddress("22 Main Street", "Claremont", "Cape Town", "7700");

        contact1 = ContactFactory.createContact("083456789", "leago2@gmail.com", address1);
        contact2 = ContactFactory.createContact("076654321", "rachel3@gmail.com", address2);
        contact3 = ContactFactory.createContact("065666777", "john4@gmail.com", address3);
    }

    @Test
    @Order(1)
    void save() {
        String url = BASE_URL + "/save";

        ResponseEntity<Contact> postResponse1 = restTemplate.postForEntity(url, contact1, Contact.class);
        assertNotNull(postResponse1);
        Contact contact = postResponse1.getBody();
        assertNotNull(contact);
        System.out.println("Saved Contact:" + contact);


        ResponseEntity<Contact> postResponse2 = restTemplate.postForEntity(url, contact2, Contact.class);
        assertNotNull(postResponse2);
        Contact contact2 = postResponse2.getBody();
        assertNotNull(contact2);
        System.out.println("Saved Contact:" + contact2);

        ResponseEntity<Contact> postResponse3 = restTemplate.postForEntity(url, contact3, Contact.class);
        assertNotNull(postResponse3);
        Contact contact3 = postResponse3.getBody();
        assertNotNull(contact3);
        System.out.println("Saved Contact:" + contact3);

    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + contact1.getEmail();

        ResponseEntity<Contact> response = restTemplate.getForEntity(url, Contact.class);
        assertNotNull(response);
        Contact contact = response.getBody();
        assertNotNull(contact);
        System.out.println("Read Contact: "+contact);

    }

    @Test
    @Order(3)
    void update() {
        String url = BASE_URL + "/update";

        Contact contact = new Contact.Builder()
                .copy(contact2)
                .setPhoneNumber("0873636972")
                .build();
        ResponseEntity<Contact> response = restTemplate.postForEntity(url, contact, Contact.class);
        assertNotNull(response);
        Contact updatedContact = response.getBody();
        assertNotNull(updatedContact);
        assertEquals("0873636972", updatedContact.getPhoneNumber());
        System.out.println("Update Contact: "+updatedContact);
    }

    @Test
    @Order(4)
    void delete() {
        String url = BASE_URL + "/delete/" +contact1.getEmail();

        restTemplate.delete(url);
        System.out.println("Delete Contact: "+contact1);
    }

    @Test
    @Order(5)
    void getall() {
        String url = BASE_URL + "/getall";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println("All Contacts: " +response.getBody());

    }
}