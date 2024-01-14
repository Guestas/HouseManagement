package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Owner;

import java.util.List;

public interface OwnerDAO {
    public Long addOwner(Owner owner);
    public Owner getOwnerById(Long id);
    public List<Owner> loadAllOwners();
    public int deleteAllOwners();
}
