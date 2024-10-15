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
import za.ac.cput.domain.AddressId;
import za.ac.cput.domain.Document;
import za.ac.cput.domain.Property;
import za.ac.cput.factory.AddressFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private static final String BASE_URL = "http://localhost:8080/StudentHomeBas/address";
    static Address address1;
    static Address address2;
    static Address address3;

    static Address saved_address1;
    static Address saved_address2;
    static Address saved_address3;



    @BeforeAll
    static void setUp() {
        address1 = AddressFactory.buildAddress("9 Lower Street", "Mowbray", "Cape Town", "5100");
        address2 = AddressFactory.buildAddress("30 Willow Street", "Hazeldone", "Cape Town", "5099");
        address3 =AddressFactory.buildAddress("Ny 6 No 106", "Guguletu", "Cape Town", "7750");
    }

    @Test
    @Order(1)
    void save() {
        String url = BASE_URL + "/save";
        ResponseEntity<Address> postResponse = testRestTemplate.postForEntity(url, address1, Address.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        System.out.println("Address saved:");
        System.out.println(postResponse.getBody());

        saved_address1 = postResponse.getBody();

    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + saved_address1.getAddressID();
        System.out.println(url);
        ResponseEntity<Address> response = testRestTemplate.getForEntity(url, Address.class);
        System.out.println(response.getBody());
        assertNotNull(response);

        assertNotNull(response.getBody());
        assertEquals(response.getBody().getStreet(), saved_address1.getStreet());

    }

    @Test
    @Order(3)
    void update() {
        String url = BASE_URL + "/update";
        Address newAddress = new Address.AddressBuilder().copy(saved_address1).setSuburb("Observatory").buildAddress();
        ResponseEntity<Address> postResponse = testRestTemplate.postForEntity(url, newAddress, Address.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(newAddress.getPostalCode(), postResponse.getBody().getPostalCode());
        System.out.println(postResponse.getBody());
    }

    @Test
    @Order(4)
    void delete() {
       // AddressId addressId = new AddressId(address1.getStreet(), address1.getPostalCode());
        String url = BASE_URL + "/delete/" + saved_address1.getAddressID();
        System.out.println("Delete Url: " + url);
        testRestTemplate.delete(url);
        System.out.println("Address Deleted successfully");
    }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println("All Addresses:");
        System.out.println(response.getBody());
    }
}