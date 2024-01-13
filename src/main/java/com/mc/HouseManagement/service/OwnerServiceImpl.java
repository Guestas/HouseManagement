package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Owner;
import com.mc.HouseManagement.repository.OwnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService{

    private final OwnerDAO ownerDAO;

    @Autowired
    public OwnerServiceImpl(OwnerDAO ownerDAO){
        this.ownerDAO = ownerDAO;
    }

    @Override
    public Long addOwner(Owner owner) {
        return ownerDAO.addOwner(owner);
    }

    @Override
    public Owner getOwnerById(Long id) {
        return null;
    }

    @Override
    public List<Owner> loadAllOwners() {
        return null;
    }
}
