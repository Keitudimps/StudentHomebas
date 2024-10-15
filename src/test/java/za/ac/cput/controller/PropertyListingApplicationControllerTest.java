//
//package za.ac.cput.controller;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import za.ac.cput.domain.*;
//import za.ac.cput.factory.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class PropertyListingApplicationControllerTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    String BASE_URL = "http://localhost:8080/StudentHomeBas/PropertylistingApplication";
//
//    PropertyListingApplication propertyApp1, propertyApp2;
//    Address address1, address2;
//    byte[] photo;
//    Document document1;
//    List<Document> photoList;
//    Name landLordName1, landLordName2;
//    Contact contact1, contact2;
//    Landlord landlordA, landlordB;
//    Property property1, property2;
//    LocalDate appliedDate, appliedDate2;
//
//    @BeforeEach
//    void setUp() {
//
//        photo = new byte[0];
//        document1 =DocumentFactory.buildDocument(79L
//                ,"ID",photo
//                , LocalDateTime.of(LocalDate.of(2024,03,24)
//                        , LocalTime.of(14,22)));
//        photoList = new ArrayList<>();
//        photoList.add(document1);
//
//        address1 = AddressFactory.buildAddress("47 Lucy Drive","Parow"
//                ,"Cape Town","7490");
//
//        address2 = AddressFactory.buildAddress("11 Olympia Crescent"
//                ,"Delville Park","George","6529");
//
//
//        landLordName1 = new Name.NameBuilder().setFirstName("Mihlai").setLastName("Tukuza").build();
//        landLordName2 = new Name.NameBuilder().setFirstName("Emily").setLastName("Thorne").build();
//
//        contact1 = ContactFactory.createContact("0786549009", "mizot24@gmail.com", address1);
//        contact2 = ContactFactory.createContact("0715468926", "emily6410@gmail.com", address2);
//
//        landlordA = LandlordFactory.buildLandlord(45673L,"Mihlai"
//                ,"Tukuza", 3, "Male"
//                , LocalDate.of(1986,8,13), "hsfs2637!"
//                ,contact1, photoList);
//
//        landlordB = LandlordFactory.buildLandlord(45696L,"Emily",
//                "Thorne", 5, "Female"
//                , LocalDate.of(1994,3,16), "Emily145Tho!!"
//                ,contact2, photoList);
//
//        property1 = PropertyFactory.buildProperty("South Point", 10,5000,
//                "10 Dorset St", "Woodstock", "Cape Town", "8001",landlord1, documentList, StatusFactory.createPendingStatus());
//
//        property2 = PropertyFactory.buildProperty( "New Market Junction", 80,3500,
//                "143 Sir Lowry Rd", "GoodWood", "Cape Town","8001",  landlord2, documentList, StatusFactory.createApprovedStatus());
//
//        appliedDate = LocalDate.of(2019, 6, 4);
//        appliedDate2 = LocalDate.of(2022, 1, 29);
//
//        propertyApp1 = PropertyListingApplicationFactory.propertyApp(appliedDate
//                , Application.Status.Accepted.toString(), property1, landlordA, property1.getPrice()
//                , property1.getAddress().getSuburb());
//
//        propertyApp2 = PropertyListingApplicationFactory.propertyApp(appliedDate2
//                , Application.Status.Pending.toString(), property2, landlordB, property2.getPrice()
//                , property2.getAddress().getSuburb());
//
//    }
//
//    @Test
//    @Order(1)
//    void save() {
//        String url = BASE_URL + "/save";
//        ResponseEntity <PropertyListingApplication> saveApp1 = restTemplate.postForEntity(url, propertyApp1, PropertyListingApplication.class);
//        ResponseEntity <PropertyListingApplication> saveApp2 = restTemplate.postForEntity(url, propertyApp2, PropertyListingApplication.class);
//        assertNotNull(saveApp1);
//        assertNotNull(saveApp2);
//        System.out.println("Application saved" + saveApp1.getBody());
//        System.out.println("----------------------------------------------------");
//        System.out.println("Application saved" + saveApp2.getBody());
//    }
//
//    @Test
//    @Order(2)
//    void read() {
//        String url = BASE_URL + "/read/" + propertyApp1.getAppNo();
//        ResponseEntity <PropertyListingApplication> readApp = restTemplate.getForEntity(url, PropertyListingApplication.class);
//        assertNotNull(readApp);
//        System.out.println("received Application: " + readApp.getBody());
//    }
//
//    @Test
//    @Order(3)
//    void update() {
//        String url = BASE_URL + "/update";
//        PropertyListingApplication statusUpdate = new PropertyListingApplication
//                .Builder().copy(propertyApp2).setStatus(Application.Status.Accepted.toString())
//                .build();
//        ResponseEntity <PropertyListingApplication> update = restTemplate.postForEntity(url, statusUpdate, PropertyListingApplication.class);
//        assertNotNull(update);
//        System.out.println("Application updated: " + update.getBody());
//    }
//
//    @Test
//    @Order(4)
//    void deleteById() {
//        String url = BASE_URL + "/read/" + propertyApp1.getAppNo();
//        restTemplate.delete(url);
//        System.out.println("Application deleted successfully:" + propertyApp1.getAppNo());
//    }
//
//    @Test
//    @Order(5)
//    void getAll() {
//        String url = BASE_URL + "/getAll";
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
//
//        System.out.println("Received Applications:");
//        System.out.println(response.getBody());
//    }
//}
