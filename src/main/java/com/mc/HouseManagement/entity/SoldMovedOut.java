package com.mc.HouseManagement.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Sold_moved_out")
public class SoldMovedOut extends Person{

    public SoldMovedOut(){}

    public SoldMovedOut(String firstName, String lastName, String email, Integer phone, Apartment apartment){
        super(firstName,lastName,email,phone,apartment);
    }

    public static SoldMovedOut createSoldMovedOut(String firstName, String lastName, String email, Integer phone, Apartment apartment){
        return new SoldMovedOut(firstName,lastName,email,phone,apartment);
    }
}
