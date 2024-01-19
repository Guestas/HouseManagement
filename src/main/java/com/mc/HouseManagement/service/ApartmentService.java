package com.mc.HouseManagement.service;

import com.mc.HouseManagement.ProcessToDo;
import com.mc.HouseManagement.entity.Apartment;

import java.util.List;

/**Description**/
public interface ApartmentService {

    /**Description**/
    public Long addUpdateApartment(Apartment apartment, ProcessToDo processToDo);

    /**Description**/
    public Apartment getApartmentById(Long id);

    /**Description**/
    public List<Apartment> loadAllApartments();
    
}
