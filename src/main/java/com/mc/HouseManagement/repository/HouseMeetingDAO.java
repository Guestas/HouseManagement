package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.HouseMeeting;

import java.util.List;

public interface HouseMeetingDAO {
    public Long addUpdateHouseMeeting(HouseMeeting houseMeeting);
    public HouseMeeting getHouseMeetingById(Long id);
    public List<HouseMeeting> loadAllHouseMeetings();
    public int deleteAllHouseMeetings();
}
