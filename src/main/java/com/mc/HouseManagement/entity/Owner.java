package com.mc.HouseManagement.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

/** Owner is blueprint for table of Owners these will be in table Persons. **/
@Entity
@DiscriminatorValue("Owner")
public class Owner extends Person{

    public Owner() {
    }


    public Owner(String firstName, String lastName, String email, Long phone, List<Apartment> apartments) {
        super(firstName, lastName, email, phone, apartments);
    }

    /** This function returns Owner with values which are described above. **/
    public static Owner createOwner(String firstName, String lastName, String email, Long phone, List<Apartment> apartments){
        return new Owner(firstName, lastName, email, phone, apartments);
    }
}
