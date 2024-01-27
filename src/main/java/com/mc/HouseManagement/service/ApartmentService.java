package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Apartment;

import java.util.List;

/**Description**/
public interface ApartmentService {

    /**
     * Adds or updates an apartment in the system.
     *
     * @param apartment The apartment to be added or updated.
     * @return The ID of the added or updated apartment.
     */
    public Long addUpdateApartment(Apartment apartment);

    /**
     * Retrieves an apartment by its ID.
     *
     * @param apartmentId The ID of the apartment to retrieve.
     * @return The apartment with the specified ID, or null if not found.
     */
    public Apartment getApartmentById(Long apartmentId);

    /**
     * Retrieves a list of all apartments in the system.
     *
     * @return A list containing all apartments in the system.
     */
    public List<Apartment> getAllApartments();
    
}
