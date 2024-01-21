package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.HouseMeeting;

import java.util.List;

public interface HouseMeetingService {
    /**
     * Adds or updates a house meeting.
     *
     * @param houseMeeting The house meeting to be added or updated.
     * @return The ID of the added or updated house meeting.
     */
    public Long addUpdateHouseMeeting(HouseMeeting houseMeeting);

    /**
     * Retrieves a house meeting by its ID.
     *
     * @param id The ID of the house meeting to retrieve.
     * @return The house meeting with the specified ID, or null if not found.
     */
    public HouseMeeting getHouseMeetingById(Long id);

    /**
     * Loads all house meetings.
     *
     * @return A list of all house meetings.
     */
    public List<HouseMeeting> loadAllHouseMeetings();

    /**
     * Deletes a house meeting by its ID.
     *
     * @param id The ID of the house meeting to delete.
     * @return The ID of the deleted house meeting.
     */
    public Long deleteHouseMeeting(Long id);

    /**
     * Adds an apartment to a house meeting.
     *
     * @param houseMeetingID The ID of the house meeting.
     * @param apartmentId    The ID of the apartment to add.
     * @return The ID of the house meeting after adding the apartment, -1 if house meeting is null or -2 if apartment is null.
     */
    public Long addApartmentToHouseMeeting(Long houseMeetingID, Long apartmentId);

    /**
     * Removes an apartment from a house meeting.
     *
     * @param houseMeetingID The ID of the house meeting.
     * @param apartmentId    The ID of the apartment to remove.
     * @return The ID of the house meeting after removing the apartment, -1 if house meeting is null or -2 if apartment is null.
     */
    public Long delApartmentFromHouseMeeting(Long houseMeetingID, Long apartmentId);
}
