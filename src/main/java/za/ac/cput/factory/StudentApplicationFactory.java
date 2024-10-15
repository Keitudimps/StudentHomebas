package za.ac.cput.factory;


import za.ac.cput.domain.Application;
import za.ac.cput.domain.Property;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.StudentApplication;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

/*
 * Author : Sabelo Kama (219149364)
 * Date : 22 April 2024
 * */
public class StudentApplicationFactory {
    public static StudentApplication studentApplication(LocalDate date,
                                                        String status, Student student,
                                                        Property property, double price){
        if(Helper.isInvalidDate(date) ||
           Helper.isNullOrEmpty(status) ||
           Helper.isObjectNull(student) ||
           Helper.isObjectNull(property) ||
           Helper.invalidDouble(price))
            return null;

        return new StudentApplication.Builder()
                .setDate(date)
                .setStatus(status)
                .setStudent(student)
                .setProperty(property)
                .setPrice(price)
                .build();
    }
}
