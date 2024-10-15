package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Contact {
    @Id
    private String email;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "contact")
    private User user;


    public Contact() {
    }

    private Contact(Builder builder) {
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.address = builder.address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(getPhoneNumber(), contact.getPhoneNumber()) &&
                Objects.equals(getEmail(), contact.getEmail()) &&
                Objects.equals(getAddress(), contact.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber(), getEmail(), getAddress());
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address= " + address +
                '}';
    }

    public static class Builder {
        private String phoneNumber;
        private String email;
        private Address address;

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder copy(Contact contact) {
            this.phoneNumber = contact.phoneNumber;
            this.email = contact.email;
            this.address = contact.address;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }
}
