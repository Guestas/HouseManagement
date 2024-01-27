package com.mc.HouseManagement.api.dto.apartment;

import com.mc.HouseManagement.entity.Apartment;

import java.util.List;

public class AddApartments {
    private List<AddApartment> addApartments;

    public AddApartments() {}

    public AddApartments(List<AddApartment> addApartments) {
        this.addApartments = addApartments;
    }

    public List<Apartment> getApartments() {
        return addApartments.stream().map(AddApartment::getApartment).toList();
    }

    public void setApartments(List<AddApartment> addApartments) {
        this.addApartments = addApartments;
    }
}
