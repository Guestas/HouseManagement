package com.mc.HouseManagement.api.dto.houseMeetings;

import com.mc.HouseManagement.entity.HouseMeeting;

import java.util.List;

public class AddUpdateHouseMeetings {
    private List<HouseMeeting> houseMeetings;

    public AddUpdateHouseMeetings() {
    }

    public AddUpdateHouseMeetings(List<HouseMeeting> houseMeetings) {
        this.houseMeetings = houseMeetings;
    }

    public List<HouseMeeting> getHouseMeetings() {
        return houseMeetings;
    }

    public void setHouseMeetings(List<HouseMeeting> houseMeetings) {
        this.houseMeetings = houseMeetings;
    }
}
