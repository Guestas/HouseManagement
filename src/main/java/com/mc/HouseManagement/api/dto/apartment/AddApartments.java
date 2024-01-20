package com.mc.HouseManagement.api.dto.apartment;

import com.mc.HouseManagement.entity.Apartment;

import java.util.List;

public class AddApartments {
    private List<Apartment> apartments;

    public AddApartments() {
    }

    public AddApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }
}
