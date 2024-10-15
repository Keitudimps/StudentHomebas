package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public  class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long userId;

    @Embedded
    protected Name name;
    protected String gender;
    protected LocalDate dateOfBirth;
    protected String password;

    @OneToOne
    @JoinColumn(name = "contact_id")
    protected Contact contact;

    protected User() {
    }
    private User(UserBuilder builder) {
        this.userId = builder.userId;
        this.name = builder.name;
        this.gender = builder.gender;
        this.dateOfBirth = builder.dateOfBirth;
        this.password = builder.password;
        this.contact = builder.contact;
    }

    public Long getUserId() {
        return userId;
    }

    public Name getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(name, user.name) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                Objects.equals(password, user.password) &&
                Objects.equals(contact, user.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, gender, dateOfBirth, password, contact);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name=" + name +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password + '\'' +
                ", contact=" + contact +
                '}';
    }

    public static class UserBuilder{
        private Long userId;
        private Name name;
        private String gender;
        private LocalDate dateOfBirth;
        private String password;
        private  Contact contact;

//        public UserBuilder copy(User user){
//          this.userId = user.getUserId();
//          this.name = user.getName();
//          this.gender = user.getGender();
//          this.dateOfBirth = user.getDateOfBirth();
//          this.password = user.getPassword();
//          this.contact = user.getContact();
//          return this;
//
//        }


        public UserBuilder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder setName(Name name) {
            this.name = name;
            return this;
        }

        public UserBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }
        public User build(){
            return new User(this);}
    }


}
