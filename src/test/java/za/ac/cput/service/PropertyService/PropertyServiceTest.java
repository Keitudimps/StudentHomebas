package za.ac.cput.service.PropertyService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.repository.DocumentRepository;
import za.ac.cput.repository.LandlordRepository;
import za.ac.cput.service.AcademicDetails.AcademicDetailsService;

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
class PropertyServiceTest {
    @Autowired
    private PropertyService service;

    private  Property property1;
    private  Property property2;
    private  Landlord landlord1;
     List<Document> documentList = new ArrayList<>();
     List<Document> pictures = new ArrayList<>();
    static BufferedImage image;
    static ByteArrayOutputStream out;
     Document document1;

     static  Property saved1;
     static Property saved2;


    @BeforeEach
     void  a_setUp() {
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
        documentList.add(document1);

        Address address1= AddressFactory.buildAddress("9 Lower Street", "Mowbray", "Cape Town", "5100");
        Contact contact = ContactFactory.createContact("0786549009", "mikerg09@gmail.com", address1);
        landlord1 = LandlordFactory.buildLandlordWithMiddleName( "Mike", "Matic",
                "September", "Male", LocalDate.of(1986,8,13), 3,
                "Mike130886",contact,documentList);

        property1 = PropertyFactory.buildProperty("South Point", 10,5000,
                "10 Dorset St", "Woodstock", "Cape Town", "8001",landlord1, documentList, StatusFactory.createPendingStatus());

        property2 = PropertyFactory.buildProperty( "New Market Junction", 80,3500,
                "143 Sir Lowry Rd", "GoodWood", "Cape Town","8001",  landlord1, documentList, StatusFactory.createApprovedStatus());
 }


    @Test
    @Order(1)
    void b_create() {
        saved1 = service.save(property1);
       assertNotNull(saved1);
       System.out.println("Saved Property: " + saved1);

        saved2 = service.save(property2);
       assertNotNull(saved2);
        System.out.println("Saved Property: " + saved2);

    }

    @Test
    @Order(2)
    void c_read() {
        Property read = service.read(saved1.getPropertyID());
        assertNotNull(read);
        System.out.println("Read Property: " + read);
    }

    @Test
    @Order(3)
    void d_update() {
            Property update = new Property.Builder()
                    .copy(saved2)
                    .setPropertyName("Rise")
                    .build();

            Property updated = service.update(update);
            assertNotNull(updated);
            System.out.println("Updated Property: " + updated);
    }

    @Test
    @Order(4)
    void e_delete() {
        System.out.println("Property To be deleted: " + saved1.getPropertyID());
        Boolean deleted = service.deleteById(property1.getPropertyID());

        assertEquals(true, deleted);
    }

    @Test
    @Order(5)
    void e_getAll() {
        System.out.println(service.getAll());
    }
}