package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Owner;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OwnerDAOImpl implements OwnerDAO{

    private final EntityManager entityManager;
    @Autowired
    public OwnerDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Long addOwner(Owner owner) {
        return entityManager.merge(owner).getId();
    }

    @Override
    public List<Owner> loadAllOwners() {
        return null;
    }

    @Override
    public Owner getOwnerById(Long id) {
        return null;
    }
}
