package za.ac.cput.domain;

/*
 * Author : Sabelo Kama (219149364)
 * Date : 22 April 2024
 * */

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
public class StudentApplication extends Application {

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    private Property property;
    private double price;

    public StudentApplication(){}

    public StudentApplication(Builder builder){
        this.date = builder.date;
        this.status = builder.status;
        this.student = builder.student;
        this.property = builder.property;
        this.price = builder.price;
    }

    public Student getStudent() {
        return student;
    }

    public Property getProperty() {
        return property;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentApplication that = (StudentApplication) o;
        return Double.compare(price, that.price) == 0 &&
                Objects.equals(student, that.student) &&
                Objects.equals(property, that.property);
    }

    @Override
    public int hashCode() {
        return
                Objects.hash(super.hashCode(), student, property, price);
    }

    @Override
    public String toString() {
        return "StudentApplication{" +
                "student='" + student + '\'' +
                ", property='" + property + '\'' +
                ", price=" + price +
                ", appNo='" + appNo + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

    public static class Builder{
        private LocalDate date;
        private String status;

        private Student student;
        private Property property;
        private double price;

         public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder setProperty(Property property) {
            this.property = property;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder copy (StudentApplication studentApp){
            this.date = studentApp.date;
            this.status = studentApp.status;
            this.student = studentApp.student;
            this.property = studentApp.property;
            this.price = studentApp.price;
            return this;
        }

        public StudentApplication build(){
            return new StudentApplication(this);
        }
    }
}
