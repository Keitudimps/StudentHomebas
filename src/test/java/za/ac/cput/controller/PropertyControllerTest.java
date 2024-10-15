package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.*;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.repository.DocumentRepository;
import za.ac.cput.repository.LandlordRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PropertyControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/StudentHomeBas/Property";


    private Property property1;
    private  Property property2;

    private static Property savedproperty1;
    private static Property savedproperty2;
    private   Landlord landlord1;
    private Landlord landlord2;
    List<Document> documentList = new ArrayList<>();
    List<Document> pictures = new ArrayList<>();
    static BufferedImage image;
    static ByteArrayOutputStream out;
    Document document1;

    @BeforeEach
    void setUp() {
        String url = "download.jpeg";

        //String certificate  = "C:\\Users\\ASUS\\Documents\\3rd-Year\\ADP3\\StudentHomebas\\LinkedIn Learning Certificate.pdf";
        try {

            image = ImageIO.read(new File(url));
            out = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", out);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        byte[] photoData = out.toByteArray();

        document1 = DocumentFactory.buildDocument("MikeSeptemeberCopyOfID", photoData, LocalDateTime.of(LocalDate.of(2024,04,11), LocalTime.of(11,14)));
        System.out.println(document1);
        documentList.add(document1);

        Address address1= AddressFactory.buildAddress("9 Lower Street", "Mowbray", "Cape Town", "5100");
        Contact contact = ContactFactory.createContact("0786549009", "mikes7@gmail.com", address1);

        landlord1 = LandlordFactory.buildLandlordWithMiddleName(88987L, "Mike", "Matic",
                "September", "Male", LocalDate.of(1986,8,13), 3,
                "Mike130886",contact,documentList);
        Contact contact2 = ContactFactory.createContact("0786549009", "mikes8@gmail.com", address1);

        landlord2 = LandlordFactory.buildLandlordWithMiddleName(88987L, "Mike", "Matic",
                "September", "Male", LocalDate.of(1986,8,13), 3,
                "Mike130886",contact2,documentList);

        property1 = PropertyFactory.buildProperty("South Point", 10,5000,
                 "10 Dorset St", "Woodstock", "Cape Town", "8001",landlord1, documentList, StatusFactory.createPendingStatus());

        property2 = PropertyFactory.buildProperty( "New Market Junction", 80,3500,
                "143 Sir Lowry Rd", "GoodWood", "Cape Town","8001",  landlord2, documentList, StatusFactory.createApprovedStatus());

    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Property> response = restTemplate.postForEntity(url, property1, Property.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Saved Property details: " + response.getBody());
        savedproperty1 = response.getBody();
        System.out.println(savedproperty1);


        ResponseEntity<Property> response2 = restTemplate.postForEntity(url, property2, Property.class);
        assertNotNull(response2);
        assertNotNull(response2.getBody());
        System.out.println("Saved Property 2 details: " + response2.getBody());
        savedproperty2 = response2.getBody();
        System.out.println(savedproperty2);
    }


    @Test
    @Order(2)
    void read() {

        String url = BASE_URL + "/read/" + savedproperty1.getPropertyID();
        String url2 = BASE_URL + "/read/" + 4;
        System.out.println("Read Link: " + url);

        ResponseEntity<Property> response = restTemplate.getForEntity(url, Property.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            assertNotNull(response.getBody());
            Property property = response.getBody();

            System.out.println("Read Property: " + property);
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            System.out.println("Property not found.");
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            System.out.println("Bad request. Invalid Id provided.");
        } else {
            System.out.println("Unexpected error: " + response.getStatusCode());
        }
    }

    @Test
    @Order(3)
    void update() {
        String url = BASE_URL + "/update";
        Property update = new Property.Builder()
                .copy(savedproperty2)
                .setPropertyName("Rise")
                .build();

        ResponseEntity<Property> response= restTemplate.postForEntity(url, update, Property.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Property updated = response.getBody();
            System.out.println("Updated Property: " + updated);
            assertEquals(updated.getPropertyID(), update.getPropertyID());
        }else{
            System.out.println("Error updating Property Details.");
        }

    }

    @Test
    @Order(4)
    void delete() {
        String url = BASE_URL + "/delete/" + savedproperty1.getPropertyID();
        restTemplate.delete(url);
    }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAll";


        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        System.out.println("All Properties Details:");
        System.out.println(response);
        System.out.println(response.getBody());


    }
}