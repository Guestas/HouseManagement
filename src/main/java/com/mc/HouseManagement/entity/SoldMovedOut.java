package com.mc.HouseManagement.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

/** SoldMovedOut is blueprint for table of Sold Moved Out these will be in table Persons. **/
@Entity
@DiscriminatorValue("Sold_moved_out")
public class SoldMovedOut extends Person{

    public SoldMovedOut(){}

    public SoldMovedOut(String firstName, String lastName, String email, Long phone, List<Apartment> apartments){
        super(firstName,lastName,email,phone,apartments);
    }

    /** This function returns SoldMovedOut with values which are described above. **/
    public static SoldMovedOut createSoldMovedOut(String firstName, String lastName, String email, Long phone, List<Apartment> apartments){
        return new SoldMovedOut(firstName,lastName,email,phone,apartments);
    }
}
