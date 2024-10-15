package za.ac.cput.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Document;
import za.ac.cput.domain.User;
import za.ac.cput.repository.ContactRepository;
import za.ac.cput.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository repo;
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public User save(User user) {
        System.out.println("user save entered");
        Contact contact = user.getContact();

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
        return repo.save(user);
    }

    @Override
    public User read(Long aLong) {
        return repo.findById(aLong).orElse(null);
    }

    @Override
    public User update(User user) {
        return repo.save(user);
    }

    @Override
    public boolean deleteById(Long aLong) {
        repo.deleteById(aLong);
        return !repo.existsById(aLong);
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }
}
