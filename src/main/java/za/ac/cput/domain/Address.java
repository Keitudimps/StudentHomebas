package za.ac.cput.domain;


import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressID;
    private String street;
    private String suburb;
    private String city;

    private String postalCode;

    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;

    protected Address(){}

    private Address(AddressBuilder builder){
        this.street = builder.street;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.postalCode = builder.postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }
    public long getAddressID() {
        return addressID;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getSuburb(), address.getSuburb()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getPostalCode(), address.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getSuburb(), getCity(), getPostalCode());
    }

    public static class AddressBuilder{
        private long addressID;
        private String street;
        private String suburb;
        private String city;
        private String postalCode;


        public AddressBuilder setStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder setSuburb(String suburb) {
            this.suburb = suburb;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }
        public AddressBuilder copy(Address address){
            this.addressID = address.addressID;
            this.street = address.street;
            this.suburb = address.suburb;
            this.city = address.city;
            this.postalCode = address.postalCode;
            return this;
        }

        public Address buildAddress(){return new Address(this);}
    }
}
