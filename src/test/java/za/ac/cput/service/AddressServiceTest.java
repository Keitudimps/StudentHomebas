package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.AddressId;
import za.ac.cput.domain.Document;
import za.ac.cput.domain.Student;
import za.ac.cput.factory.AddressFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceTest {
    @Autowired
    private AddressService service;
    static Address address1;
    static Address address2;
    static Address address3;

    @BeforeAll
    static void setUp() {
        address1 = AddressFactory.buildAddress("9 Lower Street", "Mowbray", "Cape Town", "5100");
        address2 = AddressFactory.buildAddress("30 Willow Street", "Hazeldone", "Cape Town", "5099");
        address3 =AddressFactory.buildAddress("Ny 6 No 106", "Guguletu", "Cape Town", "7750");
    }

    @Test
    @Order(1)
    void save() {
        System.out.println("========================================SAVE================================================");
        Address savedAddress = service.save(address1);
        assertNotNull(savedAddress);
        System.out.println(savedAddress);

        Address savedAddress2 = service.save(address2);
        assertNotNull(savedAddress2);
        System.out.println(savedAddress2);

        Address savedAddress3 = service.save(address3);
        assertNotNull(savedAddress3);
        System.out.println(savedAddress3);


    }

    @Test
    @Order(2)
    void read() {
        //AddressId addressId = new AddressId(address1.getStreet(), address1.getPostalCode());
        Address readAddress = service.read(address1.getAddressID());
        assertNotNull(readAddress);
        System.out.println(readAddress);
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("========================================SAVE================================================");
        Address updatedAddress = new Address.AddressBuilder().copy(address2)
                .setSuburb("Observatory")
                .buildAddress();
        Address savedAddress = service.save(updatedAddress);
        assertNotNull(savedAddress);
        System.out.println(savedAddress);
    }

    @Test
    @Order(4)
    void deleteById() {
        //AddressId addressId = new AddressId(address1.getStreet(), address1.getPostalCode());
        boolean deleted = service.deleteById(address1.getAddressID());
        assertTrue(deleted);
        System.out.println("Address deleted");
    }

    @Test
    @Order(5)
    void getall() {
        List<Address> addressList = service.getall();
        assertNotNull(addressList);
        System.out.println(addressList);
    }
}