package za.ac.cput.service.studentApplication;
/*
 * Author : Sabelo Kama (219149364)
 * Date : 24 July 2024
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.*;
import za.ac.cput.repository.StudentApplicationRepository;
import za.ac.cput.service.AcademicDetails.AcademicDetailsService;
import za.ac.cput.service.AddressService;
import za.ac.cput.service.ContactService;
import za.ac.cput.service.DocumentService;
import za.ac.cput.service.PropertyService.PropertyService;
import za.ac.cput.service.StudentService;
import za.ac.cput.service.landlordService.LandlordService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentApplicationService implements IStudentApplicationService {

    StudentApplicationRepository repository;

    @Autowired
    public StudentApplicationService(StudentApplicationRepository repository){
        this.repository = repository;
    }
    @Autowired
    ContactService contactService;
    @Autowired
    DocumentService documentService;
    @Autowired
    StudentService studentService;
    @Autowired
    LandlordService landlordService;
    @Autowired
    AcademicDetailsService academicDetailsService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    AddressService addressService;

    @Override
    public StudentApplication save(StudentApplication studentApp) {

        System.out.println("Saving all objects involved with application");
        System.out.println("Student Related objects being saved");
        Address address = studentApp.getStudent().getContact().getAddress();
        addressService.save(address);
        Contact contact = studentApp.getStudent().getContact();
        contactService.save(contact);
        AcademicDetails records = studentApp.getStudent().getAcademicDetails();
        academicDetailsService.save(records);
        List<Document> document = studentApp.getStudent().getDocuments();
        for (Document value : document){documentService.save(value);}
        Student student = studentApp.getStudent();
        studentService.save(student);

        System.out.println("Landlord Related objects being saved");
        Address addressLandlord = studentApp.getProperty().getLandlord().getContact().getAddress();
        addressService.save(addressLandlord);
        Contact contactLandlord = studentApp.getProperty().getLandlord().getContact();
        contactService.save(contactLandlord);
        List<Document> documentLandlord = studentApp.getProperty().getLandlord().getDocuments();
        for (Document value : documentLandlord){documentService.save(value);}
        Landlord landlord = studentApp.getProperty().getLandlord();
        landlordService.save(landlord);

        System.out.println("Property Related objects being saved");
        List<Document> documentProperty = studentApp.getProperty().getPictures();
        for (Document value : documentProperty){documentService.save(value);}
        Property property = studentApp.getProperty();
        propertyService.save(property);

        System.out.println("<<<All objects involved with application are saved>>>");

        return repository.save(studentApp);
    }

    @Override
    public StudentApplication read(Long appNo) {
        return repository.findById(appNo).orElse(null);
    }

    @Override
    public StudentApplication update(StudentApplication studentApp) {
        return repository.save(studentApp);
    }

    @Override
    public boolean deleteById(Long appNo) {
        repository.deleteById(appNo);
        return !repository.existsById(appNo);
    }

    @Override
    public List<StudentApplication> getAll() {
        return repository.findAll();
    }
}
