package com.dikshant.io.food.delivery.model;

public class Address {

    private String id;
    private String streetAddress;
    private String city;
    private String zipCode;
    private Location location;

    public Address(String id, String streetAddress, String city, String zipCode, Location location) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Location getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", location=" + location +
                '}';
    }
}
