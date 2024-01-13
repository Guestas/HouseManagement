package com.mc.HouseManagement.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("User")
public class User extends Person{
    public User() {
    }

    public User(String firstName, String lastName, String email, Integer phone, Apartment apartment) {
        super(firstName, lastName, email, phone, apartment);
    }

    public static User createUser(String firstName, String lastName, String email, Integer phone, Apartment apartment){
        return new User(firstName, lastName, email, phone, apartment);
    }
}
