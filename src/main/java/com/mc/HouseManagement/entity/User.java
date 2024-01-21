package com.mc.HouseManagement.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

/** User is blueprint for table of Users these will be in table Persons. **/
@Entity
@DiscriminatorValue("User")
public class User extends Person{
    public User() {
    }

    public User(String firstName, String lastName, String email, Long phone, List<Apartment> apartments) {
        super(firstName, lastName, email, phone, apartments);
    }

    /** This function returns User with values which are described above. **/
    public static User createUser(String firstName, String lastName, String email, Long phone, List<Apartment> apartments){
        return new User(firstName, lastName, email, phone, apartments);
    }
}
