package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.HouseMeeting;

import java.util.List;

public interface HouseMeetingService {
    public Long addHouseMeeting(HouseMeeting houseMeeting);
    public HouseMeeting getHouseMeetingById(Long id);
    public List<HouseMeeting> loadAllHouseMeetings();
}
