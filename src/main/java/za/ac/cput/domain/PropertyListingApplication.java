package za.ac.cput.domain;

/*
 * Author : Sabelo Kama (219149364)
 * Date : 22 April 2024
 * */

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class PropertyListingApplication extends Application {

    @ManyToOne(cascade = CascadeType.ALL)
    private Property property;

    @ManyToOne(cascade = CascadeType.ALL)
    private Landlord landlord;
    private double rentalPrice;
    private String location;

    public PropertyListingApplication(){}

    public PropertyListingApplication(Builder builder){
        this.date = builder.date;
        this.status = builder.status;
        this.property = builder.property;
        this.landlord = builder.landlord;
        this.rentalPrice = builder.rentalPrice;
        this.location = builder.location;
    }

    public Property getProperty() {
        return property;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PropertyListingApplication that = (PropertyListingApplication) o;
        return Double.compare(rentalPrice, that.rentalPrice) == 0 && Objects.equals(property, that.property) && Objects.equals(landlord, that.landlord) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), property, landlord, rentalPrice, location);
    }

    @Override
    public String toString() {
        return "PropertyListingApplication{" +
                "property='" + property + '\'' +
                ", landlord='" + landlord + '\'' +
                ", rentalPrice=" + rentalPrice +
                ", location='" + location + '\'' +
                ", appNo='" + appNo + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

    public static class Builder{
        private LocalDate date;
        private String status;
        private Property property;
        private Landlord landlord;
        private double rentalPrice;
        private String location;

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setProperty(Property property) {
            this.property = property;
            return this;
        }

        public Builder setLandlord(Landlord landlord) {
            this.landlord = landlord;
            return this;
        }

        public Builder setRentalPrice(double rentalPrice) {
            this.rentalPrice = rentalPrice;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder copy (PropertyListingApplication propertyApp){
            this.date = propertyApp.date;
            this.status = propertyApp.status;
            this.property = propertyApp.property;
            this.landlord = propertyApp.landlord;
            this.rentalPrice = propertyApp.rentalPrice;
            this.location = propertyApp.location;
            return this;
        }

        public PropertyListingApplication build(){

            return new PropertyListingApplication(this);
        }
    }
}
