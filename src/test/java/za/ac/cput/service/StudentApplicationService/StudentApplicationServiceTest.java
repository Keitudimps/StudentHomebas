
package za.ac.cput.service.StudentApplicationService;
/*
 * Author : Sabelo Kama (219149364)
 * Date : 24 July 2024
 * */

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.service.AcademicDetails.AcademicDetailsService;
import za.ac.cput.service.AddressService;
import za.ac.cput.service.ContactService;
import za.ac.cput.service.DocumentService;
import za.ac.cput.service.PropertyService.PropertyService;
import za.ac.cput.service.StudentService;
import za.ac.cput.service.landlordService.LandlordService;
import za.ac.cput.service.studentApplication.StudentApplicationService;

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

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentApplicationServiceTest {

    @Autowired
    StudentApplicationService studentApplicationService;
//    @Autowired
//    private AddressService addressService;
//    @Autowired
//    private ContactService contactService;
//    @Autowired
//    private AcademicDetailsService academicDetailsService;
//    @Autowired
//    private DocumentService documentService;
//    @Autowired
//    private LandlordService landlordService;
//    @Autowired
//    private StudentService studentService;
//    @Autowired
//    private PropertyService propertyService;

    Address address1, address2;
    byte[] photo;
    Document document1;
    List<Document> photoList;
    Name landLordName1, landLordName2, studentName1, studentName2;
    Contact contact1, contact2;
    Landlord landlordA, landlordB;
    Property property1, property2;
    Student student1, student2;
    LocalDate dateApplied;
    AcademicDetails records1, records2;
    StudentApplication studentApplication1, studentApplication2;
    static BufferedImage image;
    static ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        dateApplied = LocalDate.of(2023,8,23);

        photo = new byte[0];
        document1 =DocumentFactory.buildDocument(79L,"ID",photo
                , LocalDateTime.of(LocalDate.of(2024,03,24)
                        , LocalTime.of(14,22)));
        photoList = new ArrayList<>();
        photoList.add(document1);

        address1 = AddressFactory.buildAddress("47 Lucy Drive","Parow"
                ,"Cape Town","7490");

        address2 = AddressFactory.buildAddress("11 Olympia Crescent"
                ,"Delville Park","George","6529");


        studentName1 = new Name.NameBuilder().setFirstName("Mila").setLastName("Juices").build();
        studentName2 = new Name.NameBuilder().setFirstName("Sandy").setLastName("Downing").build();

        contact1 = ContactFactory.createContact("0786549009", "mizot24@gmail.com", address1);
        contact2 = ContactFactory.createContact("0715468926", "emily6410@gmail.com", address2);

        records1 = AcademicDetailsFactory.buildAcademicDetails("234"
                ,"UWC","Computer Sciences",2023);
        records2 = AcademicDetailsFactory.buildAcademicDetails("1689"
                ,"CPUT","Information Technology",2021);

        landlordA = LandlordFactory.buildLandlord(45673L,"Mihlai"
                ,"Tukuza", 3, "Male"
                , LocalDate.of(1986,8,13), "hsfs2637!"
                ,contact1, photoList);

        landlordB = LandlordFactory.buildLandlord(45696L,"Emily",
                "Thorne", 5, "Female"
                , LocalDate.of(1994,3,16), "Emily145Tho!!"
                ,contact2, photoList);

        String url = "download.jpeg";

        try {

            image = ImageIO.read(new File(url));
            out = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", out);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        byte[] photoData = out.toByteArray();

        List<Document> documentList = new ArrayList<>();

        document1 = DocumentFactory.buildDocument("MikeSeptemeberCopyOfID", photoData, LocalDateTime.of(LocalDate.of(2024,04,11), LocalTime.of(11,14)));
        System.out.println(document1);
        documentList.add(document1);

        property1 = PropertyFactory.buildProperty("South Point", 10,5000,
                "10 Dorset St", "Woodstock", "Cape Town", "8001",landlordA, documentList, StatusFactory.createPendingStatus());

        property2 = PropertyFactory.buildProperty( "New Market Junction", 80,3500,
                "143 Sir Lowry Rd", "GoodWood", "Cape Town","8001",  landlordB, documentList, StatusFactory.createApprovedStatus());

        student1 = StudentFactory.buildStudent(377L, "Mila","Juices"
                ,"Male", LocalDate.of(2000,11,4), "kjrgj6523./"
                , records1,contact1, photoList);

        student2 = StudentFactory.buildStudent(378L, "Sandy","Downing"
                ,"Female", LocalDate.of(2003,9,25), "kgtugkjd123!"
                , records2,contact2, photoList);


        studentApplication1 = StudentApplicationFactory.studentApplication(LocalDate.now(),
                Application.Status.Pending.toString(), student1, property1, property1.getPrice());

        studentApplication2 = StudentApplicationFactory.studentApplication(dateApplied,
                Application.Status.Accepted.toString(), student2, property2, property2.getPrice());
    }

    @Test
    @Order(1)
    void save() {

//        documentService.save(document1);
//
//        addressService.save(address1);
//        addressService.save(address2);
//
//        contactService.save(contact1);
//        contactService.save(contact2);
//
//        academicDetailsService.save(records1);
//        academicDetailsService.save(records2);
//
//        studentService.save(student1);
//        studentService.save(student2);
//
//        landlordService.save(landlordA);
//        landlordService.save(landlordB);
//
//        propertyService.save(property1);
//        propertyService.save(property2);

        StudentApplication savedApp1 = studentApplicationService.save(studentApplication1);
        StudentApplication savedApp2 = studentApplicationService.save(studentApplication2);
        assertNotNull(savedApp1);
        assertNotNull(savedApp2);
    }

    @Test
    @Order(2)
    void read() {
        StudentApplication readApp1 = studentApplicationService.read(studentApplication1.getAppNo());
        StudentApplication readApp2 = studentApplicationService.read(studentApplication2.getAppNo());
        assertNotNull(readApp1);
        assertNotNull(readApp2);
    }

    @Test
    @Order(3)
    void update() {
        StudentApplication statusUpdate = new StudentApplication.Builder().copy(studentApplication1)
                .setStatus(Application.Status.Accepted.toString()).build();
        StudentApplication update = studentApplicationService.update(statusUpdate);
        assertNotNull(update);
    }

    @Test
    @Order(4)
    void deleteById() {
        boolean delete = studentApplicationService.deleteById(studentApplication2.getAppNo());
        assertTrue(delete);
    }

    @Test
    @Order(5)
    void getAll() {
        List<StudentApplication> allRecords = studentApplicationService.getAll();
        System.out.println(allRecords.toString());
        assertNotNull(allRecords);
    }
}