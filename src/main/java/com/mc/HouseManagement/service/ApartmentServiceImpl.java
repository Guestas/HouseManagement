package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.repository.ApartmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService{

    private final ApartmentDAO apartmentDAO;

    @Autowired
    public ApartmentServiceImpl(ApartmentDAO apartmentDAO){
        this.apartmentDAO = apartmentDAO;
    }

    @Override
    public Long addUpdateApartment(Apartment apartment) {
        return apartmentDAO.addUpdateApartment(apartment);
    }

    @Override
    public Apartment getApartmentById(Long id) {
        return apartmentDAO.getApartmentById(id);
    }

    @Override
    public List<Apartment> loadAllApartments() {
        return apartmentDAO.loadAllApartments();
    }
}
