package za.ac.cput.service.landlordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.*;
import za.ac.cput.repository.ContactRepository;
import za.ac.cput.repository.DocumentRepository;
import za.ac.cput.repository.LandlordRepository;
import za.ac.cput.service.ContactService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LandlordService implements ILandlordService{
    @Autowired
    private LandlordRepository landlordRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Landlord save(Landlord landlord) {

        System.out.println("Landlord save entered");
        Contact contact = landlord.getContact();
        contactService.save(contact);
        List <Document> documents = landlord.getDocuments();
        if (documents != null) {
            documents = documents.stream()
                    .map(document -> {
                        //System.out.println("Document: " + document );
                        if (document.getDocumentId()  == null) {
                            System.out.println("no document");
                            // If document id is null, save the document directly
                            return documentRepository.save(document);

                        } else {
                            System.out.println("Existing document");
                            // If document is not null, try to find the author in the repository
                            Optional<Document> existingDocuments = documentRepository.findById(document.getDocumentId());
                            // Return the existing author if found, or save and return the new one if not found
                            return existingDocuments.orElseGet(() -> documentRepository.save(document));
                        }

                    })
                    .collect(Collectors.toList());
        }

        if (landlord != null) {
            System.out.println("Landlord: " + landlord);
            if(landlord.getUserId() == null){
                System.out.println("saving new landlord");

                landlord = landlordRepository.save(landlord);
                System.out.println("Saved");
                System.out.println("Saved landlord: " + landlord);
            }else{
                System.out.println("checking if existing landlord exists");

                Optional<Landlord> existingLandlord = landlordRepository.findById(landlord.getUserId());

                if (existingLandlord.isPresent()) {
                    System.out.println("found landlord");
                    landlord = existingLandlord.get();
                }}
        }
        return  landlord;
    }

    @Override
    public Landlord read(Long aLong) {
        return landlordRepository.findById(aLong).orElse(null);
    }

    @Override
    public Landlord update(Landlord landlord) {
        return landlordRepository.save(landlord);
    }

    @Override
    public boolean deleteById(Long aLong) {
        landlordRepository.deleteById(aLong);
        return !landlordRepository.existsById(aLong);

    }

    @Override
    public List<Landlord> getall() {
        return landlordRepository.findAll();
    }
}
