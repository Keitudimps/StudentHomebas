package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

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

class PropertyFactoryTest {
    private  Property property1;
    private  Property property2;
    private Landlord landlord1;
    List<Document> documentList = new ArrayList<>();
    List<Document> pictures = new ArrayList<>();
    static BufferedImage image;
    static ByteArrayOutputStream out;


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

      //  Document document = new DocumentFactory("Cover_image", photoData);

        Document document1 =DocumentFactory.buildDocument(001L,"MikeSeptemeberCopyOfID", photoData,LocalDateTime.of(LocalDate.of(2024,04,11), LocalTime.of(11,14)));
        System.out.println(document1);
        documentList.add(document1);

        Address address1= AddressFactory.buildAddress("9 Lower Street", "Mowbray", "Cape Town", "5100");
        Contact contact = ContactFactory.createContact("0786549009", "mikes@gmail.com", address1);
        landlord1 = LandlordFactory.buildLandlordWithMiddleName(88987L, "Mike", "Matic",
                "September", "Male", LocalDate.of(1986,8,13), 3,
                "Mike130886",contact,documentList);

        property1 = PropertyFactory.buildProperty("South Point", 10,5000,
                "10 Dorset St", "Woodstock", "Cape Town", "8001",landlord1, documentList, StatusFactory.createPendingStatus());





    }

    @Test
    void buildAcademicDetails() {
        assertNotNull(property1);
        System.out.println(property1);

    }

    @Test
    void buildAcademicDetailsWithNullValues() {
        property2 = PropertyFactory.buildProperty( "New Market Junction", 80,3500,
                "143 Sir Lowry Rd", "GoodWood", "","8001",  landlord1, documentList, StatusFactory.createApprovedStatus());

        //Test should pass because object with any null value will return null
        assertNull(property2);
        System.out.println(property2);

    }

    @Test
    void buildProperty() {
    }
}
