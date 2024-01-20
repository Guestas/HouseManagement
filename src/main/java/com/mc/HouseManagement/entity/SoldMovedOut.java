package com.mc.HouseManagement.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("Sold_moved_out")
public class SoldMovedOut extends Person{

    public SoldMovedOut(){}

    public SoldMovedOut(String firstName, String lastName, String email, Long phone, List<Apartment> apartments){
        super(firstName,lastName,email,phone,apartments);
    }

    public static SoldMovedOut createSoldMovedOut(String firstName, String lastName, String email, Long phone, List<Apartment> apartments){
        return new SoldMovedOut(firstName,lastName,email,phone,apartments);
    }
}
