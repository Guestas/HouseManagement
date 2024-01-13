package com.mc.HouseManagement.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Owner")
public class Owner extends Person{

    public Owner() {
    }

    public Owner(String firstName, String lastName, String email, Integer phone, Apartment apartment) {
        super(firstName, lastName, email, phone, apartment);
    }

    public static Owner createOwner(String firstName, String lastName, String email, Integer phone, Apartment apartment){
        return new Owner(firstName, lastName, email, phone, apartment);
    }
}
