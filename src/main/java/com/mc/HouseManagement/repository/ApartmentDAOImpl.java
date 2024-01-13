package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Apartment;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApartmentDAOImpl implements ApartmentDAO{

    private final EntityManager entityManager;
    @Autowired
    public ApartmentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Long addApartment(Apartment apartment) {
        return null;
    }

    @Override
    public Apartment getApartmentById(Long id) {
        return null;
    }

    @Override
    public List<Apartment> loadAllApartments() {
        return null;
    }
}
