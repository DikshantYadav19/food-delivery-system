package com.dikshant.io.food.delivery.model;

import java.util.Objects;

public class User {

    private String id;
    private String name;
    private long phoneNo;
    private Address address;

    public User(String id, String name, long phoneNo, Address address) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNo=" + phoneNo +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return phoneNo == user.phoneNo &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNo, address);
    }
}
