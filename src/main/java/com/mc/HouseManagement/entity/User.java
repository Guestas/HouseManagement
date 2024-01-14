package com.mc.HouseManagement.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("User")
public class User extends Person{
    public User() {
    }

    public User(String firstName, String lastName, String email, Integer phone, List<Apartment> apartments) {
        super(firstName, lastName, email, phone, apartments);
    }

    public static User createUser(String firstName, String lastName, String email, Integer phone, List<Apartment> apartments){
        return new User(firstName, lastName, email, phone, apartments);
    }
}
