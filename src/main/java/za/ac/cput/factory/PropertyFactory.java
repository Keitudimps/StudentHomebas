package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;
import za.ac.cput.util.PropertyHelper;

import java.util.List;

public class PropertyFactory {

    public static Property buildProperty(String propertyName, int numberOfRooms, double price, String streetName, String suburb, String city, String postalCode, Landlord landlord, List<Document> pictures, Status status) {

        if (PropertyHelper.isNullOrEmpty(propertyName) ||
                numberOfRooms <= 0 ||
                price <= 0 ||
                Helper.isNullOrEmpty(postalCode) ||
                Helper.isNullOrEmpty(city) ||
                Helper.isNullOrEmpty(streetName) ||
                Helper.isNullOrEmpty(suburb) ||
                Helper.isObjectNull(landlord) ||
                Helper.isListNullorEmpty(pictures)) {
            return null;
        }

        Address address = AddressFactory.buildAddress(streetName, suburb, city, postalCode);

        return new Property.Builder()
                .setPropertyName(propertyName)
                .setNumberOfRooms(numberOfRooms)
                .setAddress(address)
                .setLandlord(landlord)
                .setPictures(pictures)
                .setPrice(price)
                .setStatus(status)
                .build();
    }
}
