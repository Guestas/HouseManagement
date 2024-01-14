package com.mc.HouseManagement.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("Owner")
public class Owner extends Person{

    public Owner() {
    }

    public Owner(String firstName, String lastName, String email, Integer phone, List<Apartment> apartments) {
        super(firstName, lastName, email, phone, apartments);
    }

    public static Owner createOwner(String firstName, String lastName, String email, Integer phone, List<Apartment> apartments){
        return new Owner(firstName, lastName, email, phone, apartments);
    }
}
