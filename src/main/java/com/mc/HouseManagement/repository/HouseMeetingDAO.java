package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.HouseMeeting;

import java.util.List;

public interface HouseMeetingDAO {
    /**
     * This method adds or updates a house meeting in the database.
     *
     * @param houseMeeting The HouseMeeting object to be added or updated.
     * @return The ID of the added or updated HouseMeeting.
     */
    public Long addUpdateHouseMeeting(HouseMeeting houseMeeting);

    /**
     * This method retrieves a house meeting by its ID.
     *
     * @param id The ID of the HouseMeeting to retrieve.
     * @return The HouseMeeting with the specified ID.
     */
    public HouseMeeting getHouseMeetingById(Long id);

    /**
     * This method retrieves all house meetings from the database.
     *
     * @return A list of all HouseMeeting objects.
     */
    public List<HouseMeeting> loadAllHouseMeetings();

    /**
     * This method deletes a house meeting by its ID.
     *
     * @param id The ID of the HouseMeeting to delete.
     * @return The ID of the deleted HouseMeeting, or -1 if the deletion fails.
     */
    public Long deleteHouseMeeting(Long id);

    /**
     * This method deletes all house meetings from the database.
     *
     * @return The number of deleted HouseMeeting objects.
     */
    public int deleteAllHouseMeetings();
}
