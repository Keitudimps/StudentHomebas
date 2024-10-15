package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.ContactRepository;
import za.ac.cput.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        Contact contact = student.getContact();
        if (contact != null) {
            Optional<Contact> existingContact = contactRepository.findById(contact.getEmail());
            if (existingContact.isPresent()) {
                contact = existingContact.get();
            } else {
                contact = contactRepository.save(contact);
            }
        }
        Student updatedStudent = new Student.StudentBuilder()
                .copy(student)
                .setContact(contact)
                .build();

        return studentRepository.save(updatedStudent);  // Use 'repo' to save the ComicBook
    }


    @Override
    public Student read(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public Student update(Student student) {
        Contact contact = student.getContact();
        if (contact != null) {
            Optional<Contact> existingContact = contactRepository.findById(contact.getEmail());
            if (existingContact.isPresent()) {
                contact = existingContact.get();
            } else {
                contact = contactRepository.save(contact);
            }
        }
        Student updatedStudent = new Student.StudentBuilder()
                .copy(student)
                .setContact(contact)
                .build();

        return studentRepository.save(updatedStudent);
    }

    @Override
    public boolean deleteById(Long studentId) {

        studentRepository.deleteById(studentId);
        return !studentRepository.existsById(studentId);
    }

    @Override
    public List<Student> getall() {
        return studentRepository.findAll();
    }

    public Student authenticationByEmail(String email, String password) {
        return studentRepository.findByContactEmailAndPassword(email, password);
    }

    public Student authenticate(Long id, String password) {
        Student std = studentRepository.findById(id).orElse(null);
        if (std != null && password.equals(std.getPassword())) {
            return std;
        }
        return null;
    }

}
