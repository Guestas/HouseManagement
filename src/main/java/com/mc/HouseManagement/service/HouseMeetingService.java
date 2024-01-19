package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.HouseMeeting;

import java.util.List;

public interface HouseMeetingService {
    public Long addUpdateHouseMeeting(HouseMeeting houseMeeting);
    public HouseMeeting getHouseMeetingById(Long id);
    public List<HouseMeeting> loadAllHouseMeetings();
    public Long addApartmentToHouseMeeting(Long houseMeetingID, Long apartmentId);
    public Long delApartmentFromHouseMeeting(Long houseMeetingID, Long apartmentId);
}
