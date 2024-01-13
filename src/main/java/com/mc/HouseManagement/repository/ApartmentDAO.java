package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Apartment;

import java.util.List;

public interface ApartmentDAO {
    public Long addApartment(Apartment apartment);
    public Apartment getApartmentById(Long id);
    public List<Apartment> loadAllApartments();
}


    