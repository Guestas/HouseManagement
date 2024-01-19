package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.ProcessToDo;
import com.mc.HouseManagement.entity.Apartment;

import java.util.List;

/**Description**/
public interface ApartmentDAO{

    /**Description function functionality**/
    public Long addUpdateApartment(Apartment apartment, ProcessToDo process);

    /**Description**/
    public Apartment getApartmentById(Long id);

    /**Description**/
    public List<Apartment> loadAllApartments();

    /**Description**/
    public int deleteAllApartments();
}


    