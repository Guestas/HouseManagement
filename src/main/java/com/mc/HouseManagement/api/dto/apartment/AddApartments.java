package com.mc.HouseManagement.api.dto.apartment;

import com.mc.HouseManagement.entity.Apartment;

import java.util.List;

public class AddApartments {
    private List<AddApartment> apartments;

    public AddApartments() {
    }

    public List<Apartment> getApartments() {
        return apartments.stream().map(AddApartment::getApartment).toList();
    }

    public void setApartments(List<AddApartment> apartments) {
        this.apartments = apartments;
    }
}
