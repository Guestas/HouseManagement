package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Owner;

import java.util.List;

public interface OwnerService {
    public Long addOwner(Owner owner);
    public Owner getOwnerById(Long id);
    public List<Owner> loadAllOwners();
}
