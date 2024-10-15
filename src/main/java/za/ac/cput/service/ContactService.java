package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.AddressId;
import za.ac.cput.domain.Contact;
import za.ac.cput.repository.AddressRepository;
import za.ac.cput.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements IContactService{
    @Autowired
    private final ContactRepository contactRepository;

    @Autowired
    private  AddressService addressService;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public Contact save(Contact contact) {
        System.out.println("Entered Contact service save");
        Address address = contact.getAddress();
        addressService.save(address);


        if (contact != null) {
            System.out.println("checking if existing contact exists");

            Optional<Contact> existingContact = contactRepository.findById(contact.getEmail());


            if (existingContact.isPresent()) {
                System.out.println("found contact");
                contact = existingContact.get();
                System.out.println("Linked existing contact");
            } else {
                System.out.println("saving new contact");
                contact = contactRepository.save(contact);
                System.out.println("Saved");
            }
        }
        return  contact;


    }

    @Override
    public Contact read(String contactId) {
        return contactRepository.findById(contactId).orElse(null);
    }

    @Override
    public Contact update(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public boolean deleteById(String contactId) {
        contactRepository.deleteById(contactId);
        return !contactRepository.existsById(contactId);
    }
    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }
}
