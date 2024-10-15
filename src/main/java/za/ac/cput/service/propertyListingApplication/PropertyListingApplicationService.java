package za.ac.cput.service.propertyListingApplication;
/*
 * Author : Sabelo Kama (219149364)
 * Date : 24 July 2024
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.*;
import za.ac.cput.repository.PropertyListingApplicationRepository;
import za.ac.cput.service.AcademicDetails.AcademicDetailsService;
import za.ac.cput.service.AddressService;
import za.ac.cput.service.ContactService;
import za.ac.cput.service.DocumentService;
import za.ac.cput.service.PropertyService.PropertyService;
import za.ac.cput.service.StudentService;
import za.ac.cput.service.landlordService.LandlordService;

import java.util.List;

@Service
public class PropertyListingApplicationService implements IPropertyListingApplicationService {

    PropertyListingApplicationRepository repository;

    @Autowired
    public PropertyListingApplicationService(PropertyListingApplicationRepository repository){
        this.repository = repository;
    }
    @Autowired
    ContactService contactService;
    @Autowired
    DocumentService documentService;
    @Autowired
    LandlordService landlordService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    AddressService addressService;

    @Override
    public PropertyListingApplication save(PropertyListingApplication propertyApp) {

        System.out.println("Saving all objects involved with application");
        System.out.println("Landlord Related objects being saved");
        Address address = propertyApp.getLandlord().getContact().getAddress();
        addressService.save(address);
        Contact contact = propertyApp.getLandlord().getContact();
        contactService.save(contact);
        List<Document> document = propertyApp.getLandlord().getDocuments();
        for (Document value : document){documentService.save(value);}
        Landlord landlord = propertyApp.getProperty().getLandlord();
        landlordService.save(landlord);

        System.out.println("Property Related objects being saved");
        List<Document> documentProperty = propertyApp.getProperty().getPictures();
        for (Document value : documentProperty){documentService.save(value);}
        Property property = propertyApp.getProperty();
        propertyService.save(property);

        return repository.save(propertyApp);
    }

    @Override
    public PropertyListingApplication read(Long appNo) {
        return repository.findById(appNo).orElse(null);
    }

    @Override
    public PropertyListingApplication update(PropertyListingApplication propertyApp) {
        return repository.save(propertyApp);
    }

    @Override
    public boolean deleteById(Long appNo) {
        repository.deleteById(appNo);
        return !repository.existsById(appNo);
    }

    @Override
    public List<PropertyListingApplication> getAll() {
        return repository.findAll();
    }
}
