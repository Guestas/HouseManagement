package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Apartment;

import java.util.List;

/**Description**/
public interface ApartmentDAO{

    /**
     * Adds or updates an apartment.
     *
     * @param apartment The apartment to be added or updated.
     * @return The ID of the added or updated apartment.
     */
    public Long addUpdateApartment(Apartment apartment);

    /**
     * Retrieves an apartment by ID.
     *
     * @param id The ID of the apartment to retrieve.
     * @return The apartment with the specified ID, or null if not found.
     */
    public Apartment getApartmentById(Long id);

    /**
     * Loads all apartments.
     *
     * @return A list of all apartments.
     */
    public List<Apartment> loadAllApartments();

    /**
     * Deletes all apartments not in app just for testing.
     *
     * @return The number of apartments deleted.
     */
    public int deleteAllApartments();
}


    